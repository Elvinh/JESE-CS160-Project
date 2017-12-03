package com.sjsu.jese.parkhere.model;

/**
 * Created by evankardos on 11/30/17.
 * need to added costumer id attribute in firebase
 */

public class Review {
    String userName;
    String title;
    String description;
    int rate;


   public Review(){}

    public Review(String userName, String title, String description, int rate){
       this.description=description;
       this.title=title;
       this.userName=userName;
       this.rate=rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setRate(int rate) {
        if(0<rate || rate>5) {
            this.rate = rate;
        }
        else {
            this.rate = 1;
        }
    }

    public int getRate() {
        return this.rate;
    }
}