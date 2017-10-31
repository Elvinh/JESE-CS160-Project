package com.sjsu.jese.parkhere;

import android.media.Image;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by evankardos on 10/27/17.
 */

public class Post {
    private String mAddress;
    private String mUserId;
    private Date mDate;
    private double mPrice;
    private double mRate;
    private Image mImage;
    private ArrayList<Object> mReviews;

    /**
     * Post construtor ment to be created when
     * the user creates a post for the first time.
     * @param userId
     * @param price
     * @param rate
     * @param location
     */
    public Post(String userId, double price, double rate,String location ){
        this.mUserId=userId;
        this.mPrice=price;
        this.mRate=rate;
        this.mAddress=location;
    }

    /**
     * Post constructor ment to be created from
     * the database
     * @param userId
     * @param price
     * @param rate
     * @param location
     * @param Reviews
     */
    public Post(String userId, double price, double rate,String location ,ArrayList<Object> Reviews){
        this.mUserId=userId;
        this.mReviews=Reviews;
        this.mPrice=price;
        this.mRate=rate;
        this.mAddress=location;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public ArrayList<Object> getReviews() {
        return mReviews;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getAddress() {
        return mAddress;
    }

    public double getPrice() {
        return mPrice;
    }

    public double getRate() {
        return mRate;
    }

    public void setRate(double rate) {
        if(10 < rate || rate > 0) {
            mRate = rate;
        }
        mRate=0;
    }
}
