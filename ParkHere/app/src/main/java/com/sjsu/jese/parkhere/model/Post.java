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

    public Post(Address address, String ownerUid, double dailyRate, Date dateAvailable, Date dateEnd, String carSize, String shortDescription, String title) {
        this.address = address;
        this.ownerUid = ownerUid;
        this.dailyRate = dailyRate;
        this.dateAvailable = dateAvailable;
        this.dateEnd = dateEnd;
        this.carSize = carSize;
        this.shortDescription = shortDescription;
        this.title = title;
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

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
