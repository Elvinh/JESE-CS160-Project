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
    String dateAvailable;
    String dateEnd;
    private String uid;


    public Post() {}

    public Post(Address address, String ownerUid, double dailyRate, String carSize, String shortDescription, String title, String access) {
        this.address = address;
        this.dailyRate = dailyRate;
        this.carSize = carSize;
        this.shortDescription = shortDescription;
        this.title = title;
        this.ownerUid = ownerUid;

    }

    public Post(Address address, String ownerUid, double dailyRate, String carSize, String shortDescription, String title, String dateAvailable, String dateEnd) {
        this.address = address;
        this.ownerUid = ownerUid;
        this.dailyRate = dailyRate;
        this.carSize = carSize;
        this.shortDescription = shortDescription;
        this.title = title;
        this.dateAvailable = dateAvailable;
        this.dateEnd = dateEnd;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Address getAddress() {
        return address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

}
