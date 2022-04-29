package com.example.xdworkouttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class AlarmActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> timeCard = new ArrayList<>();
    public ArrayList<ArrayList<String>> alarmInfo;
    private AlarmCardAdapter adapter;

    public Context context;
    public Intent intent;
    public Intent intentSetUpAlarm;

    public Button setUpAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        context = AlarmActivity.this;

        recyclerView = findViewById(R.id.alarm_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //get alarm list data from file
        alarmInfo = new ArrayList<>();
        alarmInfo = AlarmDataFileHelper.readData(this);

        System.out.println(alarmInfo);

        adapter = new AlarmCardAdapter(alarmInfo, context);
        recyclerView.setAdapter(adapter);

//        intent = getIntent();
//            //TODO: alarm is getting overwritten
//        if(intent !=null) {
//            String timeEdit = intent.getStringExtra("timeSetting");
//            String ampmEdit = intent.getStringExtra("ampm");
//            String dayEdit = intent.getStringExtra("day");
//
//            //ArrayList<String> temp = new ArrayList<>(Arrays.asList(timeEdit, ampmEdit, dayEdit));
//            alarmInfo.add(timeCard);
//            adapter.notifyDataSetChanged();
//
//        }

        setUpAlarm = findViewById(R.id.setUpBtn);
        intentSetUpAlarm = new Intent(context, AlarmEditActivity.class);
        setUpAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intentSetUpAlarm);

            }
        });

    }
}