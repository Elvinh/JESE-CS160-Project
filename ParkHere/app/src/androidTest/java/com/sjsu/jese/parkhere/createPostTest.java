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
 * tests whether the post was created, first create a post then
 * assert testPostID is in firebase.post
 *
 */
@RunWith(AndroidJUnit4.class)
public class createPostTest {

    @Test
    public void useAppContext() throws Exception {
        //create post

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sjsu.jese.parkhere", appContext.getPackageName());
    }
}
