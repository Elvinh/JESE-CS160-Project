package com.sjsu.jese.parkhere;

import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PostActivityTests {
    @Test
    public void checkPostMethods() throws Exception {
        Address addr = new Address("1234 Fake St", "San Jose", "CA", 95123, "USA");

        Post p = new Post(addr, "1", 23.0, "Compact", "Mini Van", "SUV", "easy");

        assertEquals("23.0", p.getDailyRate() + "");
    }


}

