package bees.elite.ir.offnet.service;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import bees.elite.ir.offnet.activities.MainActivity;
import bees.elite.ir.offnet.activities.UserAcceptRejectRequestActivity;
import bees.elite.ir.offnet.config.Config;
import bees.elite.ir.offnet.config.PrefManager;
import bees.elite.ir.offnet.notification.GeneralNotificationModel;
import bees.elite.ir.offnet.notification.NotificationUtils;
import bees.elite.ir.offnet.user.Coupn;
import bees.elite.ir.offnet.user.UserNoteVO;



/**
 * Created by yazdandoost on 10/20/2016.
 */
public class SerajFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = SerajFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;
    private PrefManager pref;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        pref = new PrefManager(this);

        if (remoteMessage == null)
            return;
        if (remoteMessage.getData().size() > 0) {

            JSONObject json = null;
            try {
                //json = new JSONObject(remoteMessage.getData().toString());
                Gson gson = new Gson();
                GeneralNotificationModel nrm = gson.fromJson(remoteMessage.getData().toString(), GeneralNotificationModel.class);
                String noteType = nrm.getNoteType();
                Coupn coupn = nrm.getCoupn();

             //   if (noteType.equals("general")) {
                    if (remoteMessage.getNotification() != null) {
                        handleGeneralNotification(noteType, remoteMessage.getNotification().getBody(),coupn);
                    }

                    //}
                //}
            }catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }

        }


    }

    private void handleRepairmanNotFoundNotification() {

    }
    private void handleGeneralNotification(String noteType,String message,Coupn coupn) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
           Intent pushNotification = new Intent(Config.TOPIC_GLOBAL);
            pushNotification.putExtra("message", message);
           // pushNotification.putExtra("requestId", reqId);
           // pushNotification.putExtra("serviceName", serviceName);
           // pushNotification.putExtra("serviceDescription", serviceDescription);
           // pushNotification.putExtra("address", address);

            EventBus.getDefault().post(new GeneralNotificationModel(noteType,coupn));
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
            ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        /*    Gson gson = new Gson();
            String jsonOfUserVoList=pref.getUserVOList();
            Type type = new TypeToken<List<Coupn>>(){}.getType();
            List<Coupn> l=gson.fromJson(jsonOfUserVoList,type);

            l.add(userNoteVO);
            Collections.reverse(l);
            String jsonOfuserVOList = gson.toJson(l);
            pref.setUserVOList(jsonOfuserVOList);*/


                    Intent i = new Intent(getApplicationContext(), UserAcceptRejectRequestActivity.class);
                    i.putExtra("desc",coupn.getDesc());
                    i.putExtra("date", coupn.getToDate());
                    i.putExtra("amount", coupn.getAmount());
                    i.putExtra("area", coupn.getAreaName());
                    i.putExtra("category", coupn.getCategoryName());
                    i.putExtra("id", coupn.getId());
            if(!cn.getClassName().toString().equals("bees.elite.ir.offnet.activities.UserAcceptRejectRequestActivity")){
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                //showNotificationMessage(getApplicationContext(), userNoteVO.getMessage(), userNoteVO.getMessage(), null, i);
            }
            else {

                showNotificationMessage(getApplicationContext(), message, message, null, i);
            }

            //Ev*//*entBus.getDefault().post(new GeneralNotificationModel(userNoteVO));*/

            // play notification sound
          /*  NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();*/
        }else{
            // If the app is in background, firebase itself handles the notification
        }
    }
  /*  private void handleUserNotification(RepairManModel repairmanVO) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
*//*            Intent pushNotification = new Intent(Config.REPAIRMAN_INFO_PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            pushNotification.putExtra("repairmanVO", repairmanVO);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);*//*
            EventBus.getDefault().post(new NotificationForUserRMInfoModel(repairmanVO));
            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }else{
            // If the app is in background, firebase itself handles the notification
            EventBus.getDefault().post(new NotificationForUserRMInfoModel(repairmanVO));
        }
    }*/
    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            JSONObject payload = data.getJSONObject("payload");
            JSONObject noteType = data.getJSONObject("noteType");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);


            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                resultIntent.putExtra("message", message);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}
