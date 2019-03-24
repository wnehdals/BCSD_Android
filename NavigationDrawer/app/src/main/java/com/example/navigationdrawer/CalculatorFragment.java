package com.example.navigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class CalculatorFragment extends Fragment {
    private static TextView inputNum;
    private TextView result;
    private static Button oneButton;
    private static Button twoButton;
    private static Button threeButton;
    private static Button fourButton;
    private static  Button fiveButton;
    private static Button sixButton;
    private static  Button sevenButton;
    private static Button eightButton;
    private static Button nineButton;
    private static Button zeroButton;
    private static  Button divideButton;
    private static  Button multiButton;
    private static  Button plusButton;
    private static  Button minusButton;
    private static  Button dotButton;
    private static  Button clearButton;
    private static  Button sumButton;
    private static String plusoperator = "+";
    private static String minusoperator = "-";
    private static String multioperator = "*";
    private static String divideoperator = "/";
    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        inputNum = view.findViewById(R.id.num);
        ButtonSet(view);
        result = view.findViewById(R.id.result);
        Button.OnClickListener onClickListener = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.one:
                        inputNum.setText(inputNum.getText().toString()+1);
                        break;
                    case R.id.two:
                        inputNum.setText(inputNum.getText().toString()+2);
                        break;
                    case R.id.three:
                        inputNum.setText(inputNum.getText().toString()+3);
                        break;
                    case R.id.four:
                        inputNum.setText(inputNum.getText().toString()+4);
                        break;
                    case R.id.five:
                        inputNum.setText(inputNum.getText().toString()+5);
                        break;
                    case R.id.six:
                        inputNum.setText(inputNum.getText().toString()+6);
                        break;
                    case R.id.seven:
                        inputNum.setText(inputNum.getText().toString()+7);
                        break;
                    case R.id.eight:
                        inputNum.setText(inputNum.getText().toString()+8);
                        break;
                    case R.id.nine:
                        inputNum.setText(inputNum.getText().toString()+9);
                        break;
                    case R.id.zero:
                        inputNum.setText(inputNum.getText().toString()+0);
                        break;
                    case R.id.divide:
                        inputNum.setText(inputNum.getText().toString()+"/");
                        break;
                    case R.id.multi:
                        inputNum.setText(inputNum.getText().toString()+'*');
                        break;
                    case R.id.minus:
                        inputNum.setText(inputNum.getText().toString()+"-");
                        break;
                    case R.id.plus:
                        inputNum.setText(inputNum.getText().toString()+"+");
                        break;
                    case R.id.clear:
                        inputNum.setText("");
                        result.setText("");
                        break;
                    case R.id.sum:
                        String example = inputNum.getText().toString();
                        if(example.contains(plusoperator)) {
                            int length = example.length();
                            int operator = example.indexOf("+");
                            int front = Integer.parseInt(example.substring(0,operator));
                            int rear = Integer.parseInt(example.substring(operator+1,length));
                            int sum = front+rear;
                            example = Integer.toString(sum);
                        }
                        if(example.contains(minusoperator)) {
                            int length = example.length();
                            int operator = example.indexOf("-");
                            int front = Integer.parseInt(example.substring(0,operator));
                            int rear = Integer.parseInt(example.substring(operator+1,length));
                            int sum = front-rear;
                            example = Integer.toString(sum);
                        }
                        if(example.contains(multioperator)) {
                            int length = example.length();
                            int operator = example.indexOf("*");
                            int front = Integer.parseInt(example.substring(0,operator));
                            int rear = Integer.parseInt(example.substring(operator+1,length));
                            int sum = front*rear;
                            example = Integer.toString(sum);
                        }
                        if(example.contains(divideoperator)) {
                            int length = example.length();
                            int operator = example.indexOf("/");
                            int front = Integer.parseInt(example.substring(0,operator));
                            int rear = Integer.parseInt(example.substring(operator+1,length));
                            int sum = front/rear;
                            example = Integer.toString(sum);
                        }
                        result.setText(example);
                        break;
                    case R.id.dot:
                        inputNum.setText(inputNum.getText().toString()+".");
                        break;
                }
            }

        };
        oneButton.setOnClickListener(onClickListener);
        twoButton.setOnClickListener(onClickListener);
        threeButton.setOnClickListener(onClickListener);
        fourButton.setOnClickListener(onClickListener);
        fiveButton.setOnClickListener(onClickListener);
        sixButton.setOnClickListener(onClickListener);
        sevenButton.setOnClickListener(onClickListener);
        eightButton.setOnClickListener(onClickListener);
        nineButton.setOnClickListener(onClickListener);
        zeroButton.setOnClickListener(onClickListener);
        plusButton.setOnClickListener(onClickListener);
        minusButton.setOnClickListener(onClickListener);
        divideButton.setOnClickListener(onClickListener);
        multiButton.setOnClickListener(onClickListener);
        dotButton.setOnClickListener(onClickListener);
        sumButton.setOnClickListener(onClickListener);
        clearButton.setOnClickListener(onClickListener);

        return view;
    }
    void ButtonSet(View view){
        oneButton = (Button) view.findViewById(R.id.one);
        twoButton = (Button) view.findViewById(R.id.two);
        threeButton = (Button) view.findViewById(R.id.three);
        fourButton = (Button) view.findViewById(R.id.four);
        fiveButton = (Button) view.findViewById(R.id.five);
        sixButton = (Button) view.findViewById(R.id.six);
        sevenButton = (Button) view.findViewById(R.id.seven);
        eightButton = (Button) view.findViewById(R.id.eight);
        nineButton = (Button) view.findViewById(R.id.nine);
        zeroButton = (Button) view.findViewById(R.id.zero);
        divideButton = (Button) view.findViewById(R.id.divide);
        multiButton = (Button) view.findViewById(R.id.multi);
        plusButton = (Button) view.findViewById(R.id.plus);
        minusButton = (Button) view.findViewById(R.id.minus);
        dotButton = (Button) view.findViewById(R.id.dot);
        clearButton = (Button) view.findViewById(R.id.clear);
        sumButton = (Button) view.findViewById(R.id.sum);

    }



}
