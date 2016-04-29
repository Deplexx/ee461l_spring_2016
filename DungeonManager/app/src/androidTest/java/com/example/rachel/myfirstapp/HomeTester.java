package com.example.rachel.myfirstapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeTester {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MyActivity> mActivityRule = new ActivityTestRule<>(
            MyActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.name_message))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textView1))
                .check(matches(withText(mStringToBetyped)));
    }

    @Test
    public void checkView(){

    }
}