package com.sjsu.jese.parkhere.model;

/**
 * Created by Elton on 10/29/2017.
 */

public class Post {
    private Address address;
    private String ownerUid;


    public Post() {}
    
    public Post(Address address, String ownerUid) {
        this.address = address;
        this.ownerUid = ownerUid;
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
}
