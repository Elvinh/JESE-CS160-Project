package com.sjsu.jese.parkhere.model;

/**
 * Created by Elton on 10/29/2017.
 */

public class Customer {
    private String name;
    private String phone;
    private Address address;

    public Customer() {
    }

    public Customer(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
