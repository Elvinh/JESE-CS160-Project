package com.sjsu.jese.parkhere.model;

/**
 * Created by evankardos on 11/30/17.
 * need to added costumer id attribute in firebase
 */

public class Review {
    String mUserName;
    String mTitle;
    String mDescription;
    int mRate;


   public Review(){}

    public Review(String userName, String title, String description, int rate){
       mDescription=description;
       mTitle=title;
       mUserName=userName;
       mRate=rate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setRate(int rate) {
        if(0<rate || rate>5) {
            mRate = rate;
        }
        else {
            rate = 0;
        }
    }

    public int getRate() {
        return mRate;
    }
}