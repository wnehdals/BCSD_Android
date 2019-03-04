package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    public static final String KEY_SIMPLE_DATA = "data";
    TextView textView3;
    int datanumber;
    int randomnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        Random random = new Random();
        Intent intent = getIntent();
        if(intent != null) {
            datanumber = intent.getIntExtra(KEY_SIMPLE_DATA,-1);
            String a = Integer.toString(datanumber);
            Log.v("태그",a);
        }
        randomnumber = random.nextInt(datanumber+1);

        String s = Integer.toString(randomnumber);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(s);
    }
}
