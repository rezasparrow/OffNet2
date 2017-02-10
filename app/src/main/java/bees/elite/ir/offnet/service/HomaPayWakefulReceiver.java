package bees.elite.ir.offnet.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;


/**
 * Created by AZ on 2/9/2017.
 */

public class HomaPayWakefulReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, HomaPayWakefulReceiver.class);
        startWakefulService(context, service);
    }
}
