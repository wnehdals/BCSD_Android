package com.example.myfirstapp2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;
import java.util.zip.Inflater;

public class RandomFragment extends Fragment {
    View randomview;
    private int getnum;
    private String randomNumberInfo;
    int datanumber;
    int randomnumber;
    String strRandomnumber;
    String strgetnum;
    private TextView explain;
    private TextView centernum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        randomview = inflater.inflate(R.layout.fragment_random, container, false);
        getnum =((MainActivity)getActivity()).getData();
        strgetnum = Integer.toString(getnum);
        explain = randomview.findViewById(R.id.textView2);
        centernum = randomview.findViewById(R.id.textView3);

        Random random = new Random();
        randomNumberInfo = "Here is a number between 0 and";
        randomnumber = random.nextInt(getnum+1);
        strRandomnumber = Integer.toString(randomnumber);
        explain.setText(randomNumberInfo+" "+strgetnum);
        centernum.setText(strRandomnumber);
        return randomview;
    }
}

