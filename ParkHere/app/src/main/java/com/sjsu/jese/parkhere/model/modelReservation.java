package com.sjsu.jese.parkhere.model;

/**
 * Created by jerry on 11/30/17.
 */

public class modelReservation {
    private String postID;
    private String timeStart;
    private String timeEnd;
    private String UserID;
    private String Day;
    Post post;


    public modelReservation( String UserID, String Day, String timeStart, String timeEnd, String postID)
    {
        //this.post=post;
        this.postID=postID;
        this.timeStart=timeStart;
        this.timeEnd=timeEnd;
        this.UserID=UserID;
        this.Day= Day;
    }

    public modelReservation() {


    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }
}
