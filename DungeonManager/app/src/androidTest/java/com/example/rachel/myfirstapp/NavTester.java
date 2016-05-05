package com.example.rachel.myfirstapp;

/**
 * Created by Rochelle Roberts on 4/28/2016.
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.test.InstrumentationTestCase;

public class NavTester extends InstrumentationTestCase {
    private UiDevice device;

    @Override
    public void setUp() throws Exception {
        device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();
        // Wait for the Apps icon to show up on the screen
        device.wait(Until.hasObject(By.desc("Apps")), 3000);
        UiObject2 appsButton = device.findObject(By.desc("Apps"));
        appsButton.click();
        // Wait for the Calculator icon to show up on the screen
        device.wait(Until.hasObject(By.text("Dungeon Manager")), 3000);
        UiObject2 DMApp = device.findObject(By.text("Dungeon Manager"));
        DMApp.click();

    }

    // small test to show picker works
    public void testDMPicker() throws Exception {
        // Wait till picker buttons are on the screen
        device.wait(Until.hasObject(By.text("Dungeon Master")), 3000);

        // Select the dungeon master mode
        UiObject2 masterSelectButton = device.findObject(By.text("Dungeon Master"));
        masterSelectButton.click();
        device.waitForIdle(3000);

        UiObject2 resultText = device.findObject(By.clazz("android.widget.TextView"));
        boolean result = resultText.isEnabled();
        assertTrue(result);

    }

    public void testDMNavigation() throws Exception{
        // Wait till picker buttons are on the screen
        device.wait(Until.hasObject(By.text("Dungeon Master")), 3000);
        // Select the dungeon master mode
        UiObject2 masterSelectButton = device.findObject(By.text("Dungeon Master"));
        masterSelectButton.click();
        device.waitForIdle(3000);

        UiObject2 swiper = device.findObject(By.clazz("android.widget.LinearLayout"));
        swiper.swipe(Direction.RIGHT, 0.85f);
        device.waitForIdle(7000);


    }


}








