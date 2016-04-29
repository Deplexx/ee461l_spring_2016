package com.example.rachel.myfirstapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.runner.lifecycle.Stage.RESUMED;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITesting {
    Activity currentActivity;
    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<PickerActivity> mActivityRule = new ActivityTestRule<>(
            PickerActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void createCharacterTest() {
        // Type text and then press the button.
        onView(withId(R.id.buttonPlayer))
                .perform(click());
        onView(withId(R.id.name_message)).perform(typeText("Aragorn"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.dialog_yes)).perform(click());

        SharedPreferences settings = getActivityInstance().getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
        CharacterSheet chac = CharacterSheet.loadSheet(settings);

        Assert.assertEquals(chac.name, "Aragorn");
    }

    public Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()){
                    currentActivity = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity;
    }
}