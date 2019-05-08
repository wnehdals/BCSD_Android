package com.example.glideapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar progressBar;
    private Button loadButton;
    private int q;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        loadButton = (Button)findViewById(R.id.loadButton);
        loadButton.setOnClickListener(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1 == 100){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loadButton:
                Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        for(q=0;q<=100;q++){
                            progressBar.setProgress(q);
                            Message msg = handler.obtainMessage();
                            msg.arg1 = q;
                            handler.sendMessage(msg);
                            try{
                                Thread.sleep(100);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                t.start();
                break;
        }
    }
}
