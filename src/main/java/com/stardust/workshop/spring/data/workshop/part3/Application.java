package com.stardust.workshop.spring.data.workshop.part3;

import com.stardust.workshop.spring.data.workshop.part2.entities.Address;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.StatementCallback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@ComponentScan(basePackages = "com.stardust.workshop.spring.data.workshop")
public class Application {

	public static void main(String[] args) {
		ApplicationContext context
				= new AnnotationConfigApplicationContext(Application.class);

		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

		executeSample1(jdbcTemplate);

		executeSample2(jdbcTemplate);
	}

	private static void executeSample1(JdbcTemplate jdbcTemplate) {
		List<Address> results = jdbcTemplate.execute("SELECT * FROM address WHERE id=?", new PreparedStatementCallback<List<Address>>() {
			@Override
			public List<Address> doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
				preparedStatement.setInt(1, 1);
				ResultSet rs = preparedStatement.executeQuery();
				List<Address> results = new ArrayList();
				while (rs.next()) {
					Address address = new Address();
					address.setId(rs.getInt("id"));
					address.setProvince(rs.getString("province"));
					address.setCity(rs.getString("city"));
					address.setStreet(rs.getString("street"));
					results.add(address);
				}
				return results;
			}
		});

		for (Address address : results) {
			System.out.println("****** Print record ******");
			System.out.println(address);
		}
	}


	private static void executeSample2(JdbcTemplate jdbcTemplate) {
		Address address = jdbcTemplate.execute(new StatementCallback<Address>() {

			@Override
			public Address doInStatement(Statement statement) throws SQLException, DataAccessException {
				ResultSet rs = statement.executeQuery("SELECT * FROM address");
				if (rs.next()) {
					Address address = new Address();
					address.setId(rs.getInt(1));
					address.setProvince(rs.getString(2));
					address.setCity(rs.getString(3));
					address.setStreet(rs.getString(4));
					return address;
				}
				return null;
			}
		});

		System.out.println("****** Print record ******");
		System.out.println(address);
	}
}
