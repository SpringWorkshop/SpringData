package com.stardust.workshop.spring.data.workshop.part2.repositories;

import com.stardust.workshop.spring.data.workshop.part2.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("addressRepository")
public class AddressRepository implements DataRepository<Address>  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createAll() {
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        batchArgs.add(new Object [] {
                2, "湖北", "武汉", "光谷科技园"
        });
        batchArgs.add(new Object [] {
                3, "四川", "成都", "天府软件园"
        });
        jdbcTemplate.batchUpdate("INSERT INTO address VALUE (?,?,?,?)", batchArgs);

    }

    @Transactional
    @Override
    public void createOne() {
        jdbcTemplate.update("INSERT INTO address VALUE (?,?,?,?)", new Object [] {
                1, "陕西", "西安", "科技二路软件园"
        });
    }

    @Override
    public List<Address> queryAll() {
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
        return jdbcTemplate.query("SELECT * FROM address", rowMapper);
    }

    @Override
    public Address queryOne(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM address WHERE id=?", new RowMapper<Address>() {
            @Override
            public Address mapRow(ResultSet rs, int i) throws SQLException {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setProvince(rs.getString("province"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                return address;
            }
        }, new Object []{id});
    }

    @Transactional
    @Override
    public void removeAll() {
        jdbcTemplate.execute("DELETE FROM address");
    }
}
