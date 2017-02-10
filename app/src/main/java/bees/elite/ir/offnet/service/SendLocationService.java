package bees.elite.ir.offnet.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import bees.elite.ir.offnet.activities.CurrentLocation;

/**
 * Created by AZ on 2/9/2017.
 */

public class SendLocationService extends IntentService {

    private static String tagName = "SendLocationService";

    public SendLocationService() {
        super("SendLocationService");
    }

    @Override

    protected void onHandleIntent(Intent intent) {
        /*// Start your location
        LocationUtil.startLocationListener();
        try {
            // Wait for 10 seconds
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
        }
        //Stop location listener
        LocationUtil.stopLocationListener();
        // upload or save location
        uploadGps();

        SimpleWakefulReceiver.completeWakefulIntent(intent);*/
        Intent i=new Intent(getApplicationContext(), CurrentLocation.class);
        startActivity(i);

    }

}