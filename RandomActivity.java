package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    TextView textView3;
    Random random = new Random();
    public int randomnumber = random.nextInt(16);
    String s = Integer.toString(randomnumber);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(s);
    }
}
