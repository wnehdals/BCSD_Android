package com.example.myfirstapp2;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.zip.Inflater;

public class RandomFragment extends Fragment {
    private View randomview;
    private int getnum;
    private String randomNumberInfo;
    private Intent progressbarintent;
    int randomnumber;
    private String strRandomnumber;
    private String strgetnum;
    private TextView explain;
    private TextView centernum;
    private Button destroyServiceButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        progressbarintent = new Intent((MainActivity)getActivity(),MyService.class);
        ((MainActivity)getActivity()).startService(progressbarintent);
        randomview = inflater.inflate(R.layout.fragment_random, container, false);
        getnum =((MainActivity)getActivity()).getData();
        strgetnum = Integer.toString(getnum);
        explain = randomview.findViewById(R.id.textView2);
        centernum = randomview.findViewById(R.id.textView3);
        destroyServiceButton = randomview.findViewById(R.id.button4);
        Random random = new Random();
        randomNumberInfo = "Here is a number between 0 and";
        randomnumber = random.nextInt(getnum+1);
        strRandomnumber = Integer.toString(randomnumber);
        explain.setText(randomNumberInfo+" "+strgetnum);
        centernum.setText(strRandomnumber);
        //serviceIntent = new Intent(getContext(), MyService.class);

        destroyServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressbarintent = new Intent((MainActivity)getActivity(),MyService.class);
                ((MainActivity)getActivity()).stopService(progressbarintent);
            }
        });
        return randomview;
    }

}

