package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class StopWatchFragment extends Fragment {


    private TextView StopWatchTime;
    private Button StartButton;
    private Button StopButton;
    private Button PauseButton;
    private Thread TimeThread = null;
    private Boolean isRunning = true;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;
    private ArrayList<RecycleRecord> Items = new ArrayList<>();

    public StopWatchFragment() {
        // Required empty public constructor
    }

    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Start:
                    TimeThread = new Thread(new TimeThread());
                    TimeThread.start();
                    break;
                case R.id.Stop:
                    StopWatchTime.setText("00:00:00");
                    Items.clear();
                    mRecyclerAdapter.notifyItemRangeRemoved(0,mRecyclerAdapter.getItemCount());
                    mRecyclerAdapter.notifyDataSetChanged();
                    TimeThread.interrupt();
                    break;
                case R.id.Pause:
                    isRunning = !isRunning;
                    break;
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        StopWatchTime = (TextView) view.findViewById(R.id.StopWatchTime);
        BtnOnClickListener onClickListener = new BtnOnClickListener();
        StartButton = (Button) view.findViewById(R.id.Start);
        StartButton.setOnClickListener(onClickListener);
        StopButton = (Button) view.findViewById(R.id.Stop);
        StopButton.setOnClickListener(onClickListener);
        PauseButton = (Button) view.findViewById(R.id.Pause);
        PauseButton.setOnClickListener(onClickListener);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        setAdapter();


        return view;
    }

    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;


            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d", min, sec, mSec);
            StopWatchTime.setText(result);

            if(msg.arg1%1000 == 0){
                Items.add(new RecycleRecord("10초 경과 ("+result+")"));
                mRecyclerAdapter.notifyDataSetChanged();
            }

        }
    };

    public class TimeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = i++;
                    timeHandler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StopWatchTime.setText("");
                                StopWatchTime.setText("00:00:00");
                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }
    void setAdapter(){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new RecyclerAdapter(Items);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }


}


