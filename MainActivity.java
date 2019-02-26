package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static int num = 0;
    public String s = Integer.toString(num);
    TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(s);
    }
    public void doToast(View v){
        Toast.makeText(getApplicationContext(),"number is "+num, Toast.LENGTH_LONG).show();
    }
    public void doCount(View v){
        num++;
        s = Integer.toString(num);
        textView.setText(s);
    }
    public void doRandom(View v){
        Intent intent = new Intent(getApplicationContext(), RandomActivity.class);
        startActivity(intent);
    }


}
