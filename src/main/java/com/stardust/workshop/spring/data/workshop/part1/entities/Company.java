package com.stardust.workshop.spring.data.workshop.part1.entities;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Company {
    private int id;

    private String name;

    public Company(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        name = rs.getString("name");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }
}
