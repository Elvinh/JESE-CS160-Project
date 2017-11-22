package com.sjsu.jese.parkhere.Book;

/**
 * Created by sandeepsamra on 11/21/17.
 */

public class Reservation {

    private int customerid;
    private String name;
    private int phone;



    public Reservation(){

    }
    public Reservation(int customerid, String name, int phone) {
        this.customerid =customerid;
        this.name = name;
        this.phone = phone;

    }
    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) { this.customerid =customerid;}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) { this.phone = phone; }


}
