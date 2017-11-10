package com.sjsu.jese.parkhere.newPost;

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
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

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
    public void checkCalanderOption() {


        onView(withId(R.id.input_layout_start_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2017, 1, 1));
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.startDateField)).check(matches(isEditTextValueEqualTo("1/01/17")));
        //assertEquals(mActivityRule.getActivity().getNewPost().getDateAvailable(), "");

    }
}