package com.sjsu.jese.parkhere;

/**
 * Created by jerry on 11/9/17.
 */

import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Customer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *^^^ these may need to be in android test, but generally these will test values
 *
 * use mock user check Name is as expected
 */
public class userNameTest {
    @Test
    public void userName_isCorrect() throws Exception {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Customer c = new Customer("Brian", "5555555", addr);

        assertEquals("Brian" + "", c.getName() + "");
    }

}
