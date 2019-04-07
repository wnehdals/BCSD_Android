package com.example.mvc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;
import java.util.StringTokenizer;


public class CalculatorFragment extends Fragment implements View.OnClickListener {
    public static TextView result;
    public static TextView inputnum;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button divideButton;
    private Button multiButton;
    private Button plusButton;
    private Button minusButton;
    private Button clearButton;
    private Button sumButton;
    Stack<String>stack = new Stack<>();

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        result = (TextView) view.findViewById(R.id.result);
        inputnum = (TextView) view.findViewById(R.id.inputnum);
        settingButton(view);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.one:
                inputnum.setText( inputnum.getText().toString() + 1);
                break;
            case R.id.two:
                inputnum.setText( inputnum.getText().toString() + 2);
                break;
            case R.id.three:
                inputnum.setText( inputnum.getText().toString() + 3);
                break;
            case R.id.four:
                inputnum.setText( inputnum.getText().toString() + 4);
                break;
            case R.id.five:
                inputnum.setText( inputnum.getText().toString() + 5);
                break;
            case R.id.six:
                inputnum.setText( inputnum.getText().toString() + 6);
                break;
            case R.id.seven:
                inputnum.setText( inputnum.getText().toString() + 7);
                break;
            case R.id.eight:
                inputnum.setText( inputnum.getText().toString() + 8);
                break;
            case R.id.nine:
                inputnum.setText( inputnum.getText().toString() + 9);
                break;
            case R.id.zero:
                inputnum.setText( inputnum.getText().toString() + 0);
                break;
            case R.id.divide:
                inputnum.setText( inputnum.getText().toString() + "/");
                break;
            case R.id.multi:
                inputnum.setText( inputnum.getText().toString() + '*');
                break;
            case R.id.minus:
                inputnum.setText( inputnum.getText().toString() + "-");
                break;
            case R.id.plus:
                inputnum.setText( inputnum.getText().toString() + "+");
                break;
            case R.id.clear:
                result.setText("");
                inputnum.setText("");
                while (!stack.isEmpty()) {
                    stack.pop();
                }
                break;
            case R.id.sum:
                result.setText(calculate(inputnum.getText().toString()));
                break;
        }

    }
    public void settingButton(View view){
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
        clearButton = (Button) view.findViewById(R.id.clear);
        sumButton = (Button) view.findViewById(R.id.sum);
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        multiButton.setOnClickListener(this);
        sumButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }
    public static int precedence(String op){
        if(op.equals("-"))
            return 1;
        else if(op.equals("+"))
            return 1;
        else if(op.equals("/"))
            return 2;
        else if(op.equals("*"))
            return 2;
        return -1;
    }
    public static String calculate(String infix){
        String c;
        String op;
        Stack<String> stack = new Stack<>();
        StringTokenizer mStringTokenizer = new StringTokenizer(infix, "+|-|*|/", true);
        String prefix[] = new String[mStringTokenizer.countTokens()];
        int i=0;
        while (mStringTokenizer.hasMoreTokens()) {
            c = mStringTokenizer.nextToken();
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {

                while(!stack.isEmpty()){
                    op = stack.peek();
                    if (precedence(c) <= precedence(op)) {
                        prefix[i] = op;
                        stack.pop();
                        i++;
                    }
                    else
                        break;

                }
                stack.push(c);

            }
            else
            {
                prefix[i] = c;
                i++;
            }

        }
        while(!stack.isEmpty()){

            prefix[i] = stack.pop();
            i++;
        }

        for(int j=0;j<prefix.length;j++){
            c = prefix[j];

            if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")){
                int val2 = Integer.parseInt(stack.pop());
                int val1 = Integer.parseInt(stack.pop());
                if(c.equals("+")){
                    stack.push(Integer.toString(val1+val2));
                }
                else if(c.equals("-")){
                    stack.push(Integer.toString(val1-val2));
                }
                else if(c.equals("*")){
                    stack.push(Integer.toString(val1*val2));
                }
                else if(c.equals("/")){
                    stack.push(Integer.toString(val1/val2));
                }
            }
            else{
                stack.push(c);
            }
        }
        return (stack.pop());
    }
}
