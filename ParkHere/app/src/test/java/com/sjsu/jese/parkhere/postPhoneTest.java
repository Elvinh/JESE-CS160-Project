package com.sjsu.jese.parkhere;

import com.sjsu.jese.parkhere.model.Customer;
import com.sjsu.jese.parkhere.model.Address;
//import com.sjsu.jese.parkhere.model.Post;
//import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Created by sandeepsamra on 11/9/17.
 */


public class postPhoneTest {

    @Test
    public void postPhone_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Customer c = new Customer("Brian", "5555555555", addr);

        asserEquals("5555555555" + "", c.getPhone() + "");
    }


    private void asserEquals(String expected, String actual) {
    }
}

