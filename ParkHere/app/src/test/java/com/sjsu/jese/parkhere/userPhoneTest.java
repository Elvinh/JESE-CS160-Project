package com.sjsu.jese.parkhere;

import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jerry on 11/9/17.
 */

public class userPhoneTest {
    @Test
    public void userPhone_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Customer c = new Customer("Brian", "5555555555", addr);

        assertEquals("5555555555" + "", c.getPhone() + "");
    }
}
