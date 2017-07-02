package com.stardust.workshop.spring.data.workshop.part2.entities;


public class Address {
    private int id;

    private String province;

    private String city;

    private String street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "id=" + id + ",province=" + province + ",city=" + city + ",street=" + street;
    }
}
