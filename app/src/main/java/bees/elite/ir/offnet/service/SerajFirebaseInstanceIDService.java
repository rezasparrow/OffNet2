package bees.elite.ir.offnet.service;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import bees.elite.ir.offnet.config.Config;
import bees.elite.ir.offnet.config.PrefManager;


public class SerajFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = SerajFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("azzerillafcm", refreshedToken);
        // Saving reg id to shared preferences
        //Toast.makeText(getApplicationContext(), refreshedToken+"کلید", Toast.LENGTH_LONG).show();
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        PrefManager pref = new PrefManager(getApplicationContext());
        pref.setAppRegId(token);
    }
}
