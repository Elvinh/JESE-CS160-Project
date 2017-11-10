package com.sjsu.jese.parkhere;

/**
 * Created by jerry on 11/9/17.
 */

import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *^^^ these may need to be in android test, but generally these will test values
 *
 * use mock post create change price check its as expected
 */

public class postPriceTest {
    @Test
    public void postPriceTest() {
        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Post p = new Post(addr, "000000000", 10.0, "Sedan", "roomy fit, street parking", "Park in San Jose no hassle", "easy");
        assertEquals("10.0", p.getDailyRate() + ""  );
    }
}
