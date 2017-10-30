package com.sjsu.jese.parkhere.model;

/**
 * Created by Elton on 10/29/2017.
 */

public class Customer {
    private String name;
    private String email;
    private int phone;
    private Address address;
    public Customer() {
    }

    public Customer(String name, String email, int phone, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
