package com.stardust.workshop.spring.data.workshop.part2.repository;

import com.stardust.workshop.spring.data.workshop.part2.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("addressRepository")
public class AddressRepository implements DataRepository<Address>  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void createAll() {
        jdbcTemplate.update("INSERT INTO address VALUE (?,?,?,?)", new Object [] {
                1, "陕西", "西安", "科技二路"
        });
    }

    @Override
    public List<Address> queryAll() {
        return jdbcTemplate.query("SELECT * FROM address", new RowMapper<Address>() {
            @Override
            public Address mapRow(ResultSet rs, int i) throws SQLException {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setProvince(rs.getString("province"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                return address;
            }
        });
    }

    @Transactional
    @Override
    public void removeAll() {
        jdbcTemplate.execute("DELETE FROM address");
    }
}
