package com.sjsu.jese.parkhere.model;

/**
 * Created by Elton on 10/29/2017.
 */

public class Post {
    private Address address;
    private String ownerUid;
    private double dailyRate;
    String carSize;
    String shortDescription;
    String title;
    String mDateAvailable;
    String mDateEnd;


    public Post() {}

    public Post(Address address, String ownerUid, double dailyRate, String carSize, String shortDescription, String title, String access) {
        this.address = address;
        this.dailyRate = dailyRate;
        this.carSize = carSize;
        this.shortDescription = shortDescription;
        this.title = title;
        this.ownerUid = ownerUid;

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
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

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateAvailable(String dateAvailable) {
        mDateAvailable = dateAvailable;
    }

    public void setDateEnd(String dateEnd) {
        mDateEnd = dateEnd;
    }
}
