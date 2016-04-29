package com.example.rachel.myfirstapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.support.v4.content.PermissionChecker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.Permission;
import java.util.concurrent.TimeoutException;

import com.wifidirect.milan.wifidirect.WiFiDirectConstants;
import com.wifidirect.milan.wifidirect.services.WifiDirectService;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nico on 4/27/2016.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class WifiDirectTest {
    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void testWifiDirectService() throws TimeoutException {
        // Create the service Intent.
        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(),
                        WifiDirectService.class);

        // Data can be passed to the service via the Intent.
        serviceIntent.putExtra(WifiDirectService.WIFI_P2P_SERVICE, 42L);

        // Bind the service and grab a reference to the binder.
        IBinder binder = mServiceRule.bindService(serviceIntent);

        // Get the reference to the service, or you can call
        // public methods on the binder directly.
        WifiDirectService service =
                ((WifiDirectService.ServiceBinder) binder).getService();

        //refresh the list of available users
        service.refreshList();

        // Verify that the service is working correctly.
        assertEquals(service.checkCallingOrSelfPermission(Manifest.permission.ACCESS_WIFI_STATE), PERMISSION_GRANTED);
    }
}