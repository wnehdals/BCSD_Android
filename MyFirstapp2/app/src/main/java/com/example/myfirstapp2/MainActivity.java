package com.example.myfirstapp2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public int num=0;
    TextView centerNumber;
    public String stringnum = Integer.toString(num);
    RandomFragment randomFragment;
    FragmentManager fm;
    FragmentTransaction tran;
    Button bt1,bt2,bt3;
    NotificationManager notifyManager;
    NotificationCompat.Builder mBuilder;
    String channelId = "channel";
    String channelName = "Channel Name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        centerNumber = (TextView) findViewById(R.id.textView);
        centerNumber.setText(stringnum);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

        randomFragment = new RandomFragment();


        //setFrag(0);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                show();
                break;
            case R.id.button2:
                num++;
                stringnum = Integer.toString(num);
                centerNumber.setText(stringnum);
                break;
            case R.id.button3:
                setFrag(2);
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
                break;
        }
    }
    public void show(){
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("positive");
        ListItems.add("neutral");
        ListItems.add("negative");
        final CharSequence[] items = ListItems.toArray(new String[ ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setItems(items, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int pos){
                String selectedText = items[pos].toString();
                if(selectedText.equals("positive")){
                    num=0;
                    stringnum = Integer.toString(num);
                    centerNumber.setText(stringnum);
                }
                else if(selectedText.equals("neutral")){
                    Toast.makeText(MainActivity.this, stringnum, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
    public void setFrag(int n){
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        switch (n){
            case 2:
                bt1.setVisibility(View.GONE);
                bt2.setVisibility(View.GONE);
                bt3.setVisibility(View.GONE);
                tran.replace(R.id.rf,randomFragment);
                tran.commit();
                break;
        }
    }
    public int getData(){
        return num;
    }

}
