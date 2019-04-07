package com.example.mvc;

import android.content.Intent;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.mvc.MainActivity.Items;
import static com.example.mvc.MainActivity.mRecyclerAdapter;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addButton;
    private EditText editTitle;
    private EditText editContent;
    private EditText editName;
    private TextView name;
    private TextView title;
    String formatDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        getSupportActionBar().setTitle("게시판작성");
        addButton = (Button) findViewById(R.id.plus);
        addButton.setOnClickListener(this);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editName = (EditText) findViewById(R.id.editName);
        editContent = (EditText) findViewById(R.id.editContent);
        name = (TextView) findViewById(R.id.name);
        title = (TextView) findViewById(R.id.title);
        long now = System.currentTimeMillis();
        Date timedate = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        formatDate = dateFormat.format(timedate);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.plus:
                Items.add(new RecycleRecord(editTitle.getText().toString()+"            ["+editName.getText().toString()+"]"+formatDate));
                mRecyclerAdapter.notifyDataSetChanged();
                Intent intent = new Intent(this,MainActivity.class);
                //intent.putExtra("name",editName.getText().toString());
                //intent.putExtra("title",editTitle.getText().toString());
                //intent.putExtra("date",formatDate);
                startActivity(intent);

        }
    }
}
