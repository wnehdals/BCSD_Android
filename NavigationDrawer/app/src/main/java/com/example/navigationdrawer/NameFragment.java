package com.example.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class NameFragment extends Fragment {
    private ArrayList<String> Items;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private EditText name;
    private Button addButton;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        Items = new ArrayList<String>();
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Items);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        name = (EditText)view.findViewById(R.id.editText);
        addButton = (Button)view.findViewById(R.id.plus);
        addButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputnameText = name.getText().toString();
                Items.add(inputnameText);
                name.setText("");
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }




}
