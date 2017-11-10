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
 * use mock post create change Address check its as expected
 */
public class postAddressTest {
    @Test
    public void postAddress_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Post p = new Post(addr, "000000000", 10.0,"Sedan", "roomy fit, street parking", "Park in San Jose no hassle", "easy");

        assertEquals( addr +"", p.getAddress()+"" );
    }


    @Test
    public void checkPostAddressSetterAndGetters() throws Exception {
        Address addr = new Address("1234 Fake St", "San Jose", "CA", 95123, "USA");

        Post p = new Post(addr, "1", 23.0, "Compact", "Mini Van", "SUV", "easy");

        assertEquals(addr+"", p.getAddress() + "");
        Address addr2 = new Address("12 Fake St", "Cambpell", "CA", 95123, "USA");
        p.setAddress(addr2);
        assertEquals(addr2 + "", p.getAddress() + "");
    }

/*
*removed due to us not currently having this functionality
*
    @Test
    public void postChangeAddress_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Address addr2 = new Address("1 IDK", "Some place", "NC", 63545, "USA");
        Post p = new Post(addr, "000000000", 10.0,"Sedan", "roomy fit, street parking", "Park in San Jose no hassle", "easy");
        //command to change address
        assertEquals( addr2, p.getAddress() );
    }
*/
}