package com.sjsu.jese.parkhere.model;

import java.util.Date;

/**
 * Created by Elton on 10/29/2017.
 */

public class Post {
    private Address address;
    private String ownerUid;
    private double dailyRate;
    private Date dateAvailable;
    private Date dateEnd;
    String carSize;
    String shortDescription;
    String title;

    public Post() {}

    public Post(Address address, String ownerUid, Date dateListed, double baseRatePay) {
        this.address = address;
        this.ownerUid = ownerUid;
        this.dailyRate = dailyRate;
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

    public double getBaseRatePay() {
        return dailyRate;
    }

    public void setBaseRatePay(double dailyRate) {
        this.dailyRate = dailyRate;
    }
}
