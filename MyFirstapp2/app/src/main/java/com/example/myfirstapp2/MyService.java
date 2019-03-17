package com.example.myfirstapp2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public NotificationManager notifyManager;
    public NotificationCompat.Builder mBuilder;
    public String channelId = "channel";
    public String channelName = "Channel Name";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("StartService","onCreat()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId,channelName,importance);
            notifyManager.createNotificationChannel(mChannel);
        }
        mBuilder =
                new NotificationCompat.Builder(getApplicationContext(),channelId);
        Intent notificationIntent = new Intent(getApplicationContext(),MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int requestID = (int) System.currentTimeMillis();
                /*
                PendingIntent pendingIntent = new pendingIntent.getActivity(getApplicationContext()
                        ,requestID
                        ,notificationIntent
                        ,PendingIntent.FLAG_UPDATE_CURRENT);
                        */

        mBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Progressbar")
                .setContentText("progressbar")
                .setDefaults(Notification.DEFAULT_ALL);
        mBuilder.setProgress(0,0,true);
        notifyManager.notify(0,mBuilder.build());
        Log.d("StartService","onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("StartService","onDestroy");
        super.onDestroy();
    }
}
