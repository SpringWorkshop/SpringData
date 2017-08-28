package com.stardust.workshop.spring.data.workshop.part4.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public abstract class DataRepository<T> {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    protected abstract String getEntityClassName();

    protected abstract String getEntityName();

    public T find(long id) {
        return (T) hibernateTemplate.get(getEntityClassName(), id);
    }

    @Transactional
    public List<?> query(Map<String, Object> filters) {
        String selector = "from " + getEntityName() + " t";
        StringBuilder query = new StringBuilder(selector);
        filters.forEach((k, v) -> {
            if (selector.equals(query.toString())) {
                query.append(" where ");
            } else {
                query.append(" and ");
            }
            query.append("t." + k + "=?");
        });

        return hibernateTemplate.find(query.toString(), filters.values().toArray());
    }

    @Transactional
    public void save(T entity) {
        hibernateTemplate.save(entity);
    }
}
