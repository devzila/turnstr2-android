package com.wdp.Firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sendbird.android.SendBird;
import com.wdp.ActivityScreen.MainActivity;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH;
import static androidx.core.app.NotificationManagerCompat.IMPORTANCE_LOW;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String token) {
        Log.d("token","------->" + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
        commonSharedPreference.setFCMToken(this, token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload---->" +  remoteMessage.getData());
                Log.d("remoteMessage", "----->" + remoteMessage.getNotification().getBody());
            }
            if (remoteMessage.getNotification() != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent("GizmohJoin");
                        myIntent.putExtra("message",remoteMessage.getNotification().getBody());
                        sendBroadcast(myIntent);
                    }
                },500);

            }

            String channelUrl = null;

            try {
                if (remoteMessage.getData().containsKey("sendbird")) {
                    JSONObject sendBird = new JSONObject(remoteMessage.getData().get("sendbird"));
                    JSONObject channel = (JSONObject) sendBird.get("channel");
                    channelUrl = (String) channel.get("channel_url");
                    SendBird.markAsDelivered(channelUrl);
                    Log.d("channelUrl", "----->" + channelUrl);
                    sendNotification(getApplicationContext(), remoteMessage.getData().get("message"), channelUrl);
                }
                else {
                    if (remoteMessage.getData().get("channel") != null) {
                        String channel_id = remoteMessage.getData().get("channel");
                        createLocalNotification(getApplicationContext(), remoteMessage.getNotification().getBody(), channel_id);
                    }
                    else {
                        createLocalNotification(getApplicationContext(), remoteMessage.getNotification().getBody(),"");
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public static void sendNotification(Context context, String messageBody, String channelUrl) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final String CHANNEL_ID = "CHANNEL_ID";
        if (Build.VERSION.SDK_INT >= 26) {  // Build.VERSION_CODES.O
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("groupChannelUrl", channelUrl);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setColor(Color.parseColor("#7469C4"))  // small icon background color
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_login))
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent);
        notificationBuilder.setContentText(messageBody);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    private void createLocalNotification(Context context,String message, String chanel_id) {
        String CHANNEL_ID = "GizmohGroupChannel";
        String CHANNEL_NAME = "Public Channel";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("GizmohGroup");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            try {
                notificationManager.createNotificationChannel(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d("chanel_id","-->" + chanel_id);
        Intent intent = null;
        if (!TextUtils.isEmpty(chanel_id)) {
            intent = new Intent(context, MainActivity.class);
            intent.putExtra("chanel_id", chanel_id);
        }
        else{
            intent = new Intent(context, MainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setNumber(1);
        mBuilder.setSound(uri).setPriority(NotificationCompat.PRIORITY_MAX | IMPORTANCE_HIGH);
        Notification notification = mBuilder.build();
        int id = (int) System.currentTimeMillis();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(id, notification);
    }


 }