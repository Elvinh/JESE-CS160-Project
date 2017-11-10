package com.sjsu.jese.parkhere.newPost;

import android.app.DatePickerDialog;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.sjsu.jese.parkhere.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.sjsu.jese.parkhere.login.LoginActivityTest.hasTextInputLayoutHintError;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import android.support.test.espresso.contrib.PickerActions;

/**
 * Created by Elton on 11/9/2017.
 */
@RunWith(AndroidJUnit4.class)
public class NewPostActivityTest {
    @Rule
    public ActivityTestRule<NewPostActivity> mActivityRule =
            new ActivityTestRule<>(NewPostActivity.class);

    Matcher<View> isEditTextValueEqualTo(final String content) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Match Edit Text Value with View ID Value : :  " + content);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    return false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text =((TextView) view).getText().toString();
                    } else {
                        text =((EditText) view).getText().toString();
                    }

                    return (text.equalsIgnoreCase(content));
                }
                return false;
            }
        };
    }

    @Test
    public void checkCarSizeSpinner() {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Mini Van"))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Mini Van"))));
        assertEquals(mActivityRule.getActivity().getNewPost().getCarSize(), "Mini Van");
    }

   @Test
    public void checkZipCodeError() {
       onView(withId(R.id.nextBtn)).perform(click());
       onView(withId(R.id.nextBtn)).perform(click());


       onView(withId(R.id.zipField))
               .perform(typeText("test"), closeSoftKeyboard());


       onView(withId(R.id.nextBtn)).perform(click());

       onView(withId(R.id.input_layout_zip))
               .check(matches(hasTextInputLayoutHintError("Not a valid zip code.")));
   }

    @Test
    public void checkZipCodeNoError() {
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());


        onView(withId(R.id.zipField))
                .perform(typeText("95132"), closeSoftKeyboard());


        onView(withId(R.id.input_layout_zip))
                .check(matches(not(hasTextInputLayoutHintError("Not a valid zip code."))));

        onView(withId(R.id.nextBtn)).perform(click());
    }

    @Test
    public void checkAddressSetInPost() {
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());

        onView(withId(R.id.stateField))
                .perform(typeText("CA"), closeSoftKeyboard());
        onView(withId(R.id.cityField))
                .perform(typeText("Milpitas"), closeSoftKeyboard());
        onView(withId(R.id.countryField))
                .perform(typeText("United States"), closeSoftKeyboard());
        onView(withId(R.id.streetField))
                .perform(typeText("2196 Mesa Verde Dr."), closeSoftKeyboard());
        onView(withId(R.id.zipField))
                .perform(typeText("95132"), closeSoftKeyboard());



        onView(withId(R.id.nextBtn)).perform(click());

        int zip = 95132;
        assertEquals(mActivityRule.getActivity().getNewPost().getAddress().getZipCode(), zip);
        assertEquals(mActivityRule.getActivity().getNewPost().getAddress().getState(), "CA");
        assertEquals(mActivityRule.getActivity().getNewPost().getAddress().getCity(), "Milpitas");
        assertEquals(mActivityRule.getActivity().getNewPost().getAddress().getCountry(), "United States");
        assertEquals(mActivityRule.getActivity().getNewPost().getAddress().getStreetAddress(), "2196 Mesa Verde Dr.");
    }

    @Test
    public void checkDatesSetInPost() {
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());

        assertNotNull(mActivityRule.getActivity().getNewPost().getDateAvailable());
        assertNotNull(mActivityRule.getActivity().getNewPost().getDateEnd());
    }
}
