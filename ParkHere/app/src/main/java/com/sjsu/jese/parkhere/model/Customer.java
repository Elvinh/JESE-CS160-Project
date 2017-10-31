package com.sjsu.jese.parkhere.model;

/**
 * Created by Elton on 10/29/2017.
 */

public class Customer {
    private String name;
    private int phone;
    private Address address;


    public Customer() {
    }

    public Customer(String name, int phone, Address address) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
