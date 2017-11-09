package com.sjsu.jese.parkhere;

/**
 * Created by jerry on 11/9/17.
 */

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * tests whether a User was created
 */
@RunWith(AndroidJUnit4.class)
public class createUserTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sjsu.jese.parkhere", appContext.getPackageName());
    }
}
