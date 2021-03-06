package com.sjsu.jese.parkhere;

/**
 * Created by jerry on 11/9/17.
 */

import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;

import com.sjsu.jese.parkhere.browsePost.PostData;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * tests whether the post was created, first create a post then
 * assert testPostID is in firebase.post
 *
 */

public class postGetPostIdTest {

    @Test
    public void getPostID() throws Exception {
        //get post

        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Post p = new Post(addr, "000000000", 10.0,"Sedan", "roomy fit, street parking", "Park in San Jose no hassle", "easy");
        assertEquals("000000000" + "", p.getOwnerUid() + "");

    }

    //retrospect this was unnecessary
    @Test
    public void FailgetPostID() throws Exception {
        //fail get post

        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Post p = new Post(addr, "000000000", 10.0,"Sedan", "roomy fit, street parking", "Park in San Jose no hassle", "easy");
        assertFalse("this test was designed to fail" ,p.getOwnerUid().equals("01203103"));

    }

    @Test
    public void checkPostOwnerIdSetterAndGetters() throws Exception {
        Address addr = new Address("1234 Fake St", "San Jose", "CA", 95123, "USA");
        Post p = new Post(addr, "1", 23.0, "Compact", "Mini Van", "SUV", "easy");
        assertEquals("1", p.getOwnerUid());

        p.setOwnerUid("3");
        assertEquals("3" + "", p.getOwnerUid() + "");
    }
}
