package com.sjsu.jese.parkhere.login;


import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.sjsu.jese.parkhere.R;

import static android.support.test.espresso.Espresso.onView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by Elton on 11/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
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
    @Test
    public void checkEmailField() {
        onView(withId(R.id.emailText))
                .perform(typeText("test@test.com"), closeSoftKeyboard());

        onView(withId(R.id.emailText))
                .check(matches(withText("test@test.com")));

    }

    @Test
    public void checkPasswordField() {
        onView(withId(R.id.passwordText))
                .perform(typeText("tester"), closeSoftKeyboard());

        onView(withId(R.id.passwordText))
                .check(matches(withText("tester")));

    }

    @Test
    public void checkEmptyEmailFieldError() {
        // click sign in button, meaning nothing was inputted
        onView(withId(R.id.signInBtn))
                .perform(click());
        // check if error says "Required."
        onView(withId(R.id.input_layout_email))
                .check(matches(hasTextInputLayoutHintError("Required.")));

    }

    @Test
    public void checkNotEmptyEmailField() {
        // input email into field
        onView(withId(R.id.emailText))
                .perform(typeText("test@test.com"), closeSoftKeyboard());

        // click sign in button
        onView(withId(R.id.signInBtn))
                .perform(click());

        // check if error is null "Required."
        onView(withId(R.id.input_layout_email))
                .check(matches(not(hasTextInputLayoutHintError("Required."))));

    }

    @Test
    public void checkEmptyPasswordFieldError() {
        // click sign in button, meaning nothing was inputted
        onView(withId(R.id.signInBtn))
                .perform(click());
        // check if error says "Required."
        onView(withId(R.id.input_layout_password))
                .check(matches(hasTextInputLayoutHintError("Required.")));

    }

    @Test
    public void checkNotEmptyPasswordField() {
        // input email into field
        onView(withId(R.id.passwordText))
                .perform(typeText("tester"), closeSoftKeyboard());

        // click sign in button
        onView(withId(R.id.signInBtn))
                .perform(click());

        // check if error is null "Required."
        onView(withId(R.id.input_layout_password))
                .check(matches(not(hasTextInputLayoutHintError("Required."))));

    }
}
