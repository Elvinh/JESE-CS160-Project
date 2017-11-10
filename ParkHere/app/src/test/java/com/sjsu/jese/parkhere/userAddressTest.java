package com.sjsu.jese.parkhere;

/**
 * Created by jerry on 11/9/17.
 */

import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;
import com.sjsu.jese.parkhere.model.Post;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *^^^ these may need to be in android test, but generally these will test values
 *
 * use mock user create change Address check its as expected
 */
public class userAddressTest {
    @Test
    public void setCity_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");

        assertTrue(addr.getCity().equals("San Jose"));

        addr.setCity("New York");
        assertTrue(addr.getCity().equals("New York"));
    }

    @Test
    public void setStreet_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        assertTrue(addr.getStreetAddress().equals("1 raider way"));

        addr.setCity("123 bob court");
        assertTrue(addr.getCity().equals("123 bob court"));
    }

    @Test
    public void setzip_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        assertTrue(addr.getZipCode()==95112);

        addr.setZipCode(50123);
        assertTrue(addr.getZipCode()==50123);
    }

    @Test
    public void setState_isCorrect() throws Exception {
        Address addr = new Address("1 raider way",
                "San Jose", "CA", 95112, "USA");
        assertTrue(addr.getState().equals("CA"));

        addr.setState("NY");
        assertTrue(addr.getState().equals("NY"));
    }

    @Test
    public void setCountry_isCorrect() throws Exception {
        Address addr = new Address("1 raider way",
                "San Jose", "CA", 95112, "USA");
        assertTrue(addr.getCountry().equals("USA"));

        addr.setState("Canada");
        assertTrue(addr.getState().equals("Canada"));
    }
}
