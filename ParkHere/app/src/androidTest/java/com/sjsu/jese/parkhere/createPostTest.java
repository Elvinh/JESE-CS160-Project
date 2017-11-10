package com.sjsu.jese.parkhere;

/**
 * Created by jerry on 11/9/17.
 */

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.sjsu.jese.parkhere.browsePost.PostData;
import com.sjsu.jese.parkhere.login.LoginActivity;
import com.sjsu.jese.parkhere.model.Address;
import com.sjsu.jese.parkhere.model.Post;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    public static Matcher<View> hasTextInputLayoutHintError(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

//in progress
    @Test
    public void createapost() throws Exception {
        //create post
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.submitBtn)).perform(click());

        Address addr = new Address("1 raider way", "San Jose", "CA", 95112, "USA");
        Post p = new Post(addr, "000000000", 10.0,"Sedan", "roomy fit, street parking", "Park in San Jose no hassle", "easy");
        assertEquals(addr+ "", p.getAddress() + "");
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();

        //assertEquals("com.sjsu.jese.parkhere", appContext.getPackageName());
    }
}
