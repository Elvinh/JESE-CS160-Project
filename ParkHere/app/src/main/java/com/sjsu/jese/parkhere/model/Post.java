package com.sjsu.jese.parkhere.model;

import java.util.Date;

/**
 * Created by Elton on 10/29/2017.
 */

public class Post {
    private Address address;
    private String ownerUid;
    private Date dateListed;
    private double baseRatePay;

    public Post() {}

    public Post(Address address, String ownerUid, Date dateListed, double baseRatePay) {
        this.address = address;
        this.ownerUid = ownerUid;
        this.dateListed = dateListed;
        this.baseRatePay = baseRatePay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public Date getDateListed() {
        return dateListed;
    }

    public void setDateListed(Date dateListed) {
        this.dateListed = dateListed;
    }

    public double getBaseRatePay() {
        return baseRatePay;
    }

    public void setBaseRatePay(double baseRatePay) {
        this.baseRatePay = baseRatePay;
    }
}
