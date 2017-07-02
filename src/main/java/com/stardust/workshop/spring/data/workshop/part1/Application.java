package com.stardust.workshop.spring.data.workshop.part1;

import com.stardust.workshop.spring.data.workshop.part1.entities.Company;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.sql.*;

@ComponentScan(basePackages = "com.stardust.workshop.spring.data.workshop")
public class Application {

	private static void createRecords(DataSource ds) {
		Connection connection = null;
		try {
			// create connection
			connection = ds.getConnection();
			// enable transaction
			connection.setAutoCommit(false);

			// execute SQL
			Statement stmt = connection.createStatement();
			stmt.execute("DELETE FROM companies");

			PreparedStatement ps = connection.prepareStatement("INSERT INTO companies VALUES (?,?)");
			ps.setInt(1, 1);
			ps.setString(2, "Great Company");
			ps.addBatch();
			ps.setInt(1, 2);
			ps.setString(2, "Great Company 2");
			ps.addBatch();
			ps.executeBatch();

			// commit transaction
			connection.commit();
		} catch (SQLException e) {
			System.out.println("****** SQL Error ******");

			// rollback transaction
			try {
				System.out.println("****** Starting rollback ******");
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("****** Failed rollback ******");
			}
		} finally {
			// close connection
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("****** Failed to release db connection ******");
			}
		}
	}

	private static void queryRecords(DataSource ds) {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			// execute query
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM companies WHERE name LIKE ?");
			ps.setString(1, "%Great Company%");
			rs = ps.executeQuery();
			// mapping to entity and print it out
			while (rs.next()) {
				System.out.println("****** Print record ******");
				System.out.println(new Company(rs));
			}
		} catch (SQLException e) {
			System.out.println("****** SQL Error ******");
		} finally {
			// close connection and result set
			try {
				rs.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("****** Failed to release db connection ******");
			}
		}
	}

	public static void main(String[] args) {
		ApplicationContext context
				= new AnnotationConfigApplicationContext(Application.class);

		DataSource ds = context.getBean(DataSource.class);

		createRecords(ds);

		queryRecords(ds);
	}
}
