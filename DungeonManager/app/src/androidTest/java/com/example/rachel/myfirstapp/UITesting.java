package com.example.rachel.myfirstapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Rule
    public ActivityTestRule<PickerActivity> mActivityRule = new ActivityTestRule<>(
            PickerActivity.class);

    @Test
    public void createNameTest() {
        // Type text and then press the button.
        onView(withId(R.id.buttonPlayer))
                .perform(click());
        onView(withId(R.id.name_message)).perform(typeText("Aragorn"));
        onView(withId(R.id.type_message)).perform(typeText("Ranger"));
        onView(withId(R.id.class_message)).perform(typeText("Human"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.dialog_yes)).perform(click());

        SharedPreferences settings = getActivityInstance().getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
        CharacterSheet chac = CharacterSheet.loadSheet(settings);

        Assert.assertEquals(chac.name, "Aragorn");
        Assert.assertEquals(chac.race, "Ranger");
        Assert.assertEquals(chac.cclass, "Human");
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