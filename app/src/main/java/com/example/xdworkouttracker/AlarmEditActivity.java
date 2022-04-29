package com.example.xdworkouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog;
import com.anurag.multiselectionspinner.MultiSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlarmEditActivity extends AppCompatActivity implements MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener{

    private Button save_button;
    private Button delete_button;
    Spinner hour_spinner;
    Spinner min_spinner;
    Spinner amPm_spinner;

    private ArrayList<String> timeCard = new ArrayList<>();
    public ArrayList<ArrayList<String>> alarmInfo = new ArrayList<>();
    private AlarmCardAdapter adapter;


    MultiSpinner multiSpinner;
    Intent intent;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

        context = AlarmEditActivity.this;


        //HOUR SPINNER
        //set spinner drop down items
        hour_spinner = (Spinner) findViewById(R.id.hour_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> hour_adapter = ArrayAdapter.createFromResource(this,
                R.array.hour_list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour_spinner.setAdapter(hour_adapter);


        //MINUTES SPINNER
        min_spinner = (Spinner) findViewById(R.id.minute_spinner);
        ArrayAdapter<CharSequence> min_adapter = ArrayAdapter.createFromResource(this,
                R.array.min_list, android.R.layout.simple_spinner_item);
        min_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        min_spinner.setAdapter(min_adapter);



        //AM/PM SPINNER
        amPm_spinner = (Spinner) findViewById(R.id.AmPm_spinner);
        ArrayAdapter<CharSequence> amPm_adapter = ArrayAdapter.createFromResource(this,
                R.array.amPm_list, android.R.layout.simple_spinner_item);
        amPm_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amPm_spinner.setAdapter(amPm_adapter);



        //REPEAT PATTERN SPINNER
//        Spinner pattern_spinner = (Spinner) findViewById(R.id.pattern_spinner);
//        ArrayAdapter<CharSequence> pattern_adapter = ArrayAdapter.createFromResource(this,
//                R.array.repeatpattern_list, android.R.layout.simple_spinner_item);
//        pattern_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        pattern_spinner.setAdapter(pattern_adapter);
//        pattern_spinner.setOnItemSelectedListener(new DialogInterface.OnMultiChoiceClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//
//            }
//
//        });

        multiSpinner= findViewById(R.id.spinnerMultiSpinner);
        ArrayList<String> contentList = new ArrayList<String>();
        contentList.add("Sunday");
        contentList.add("Monday");
        contentList.add("Tuesday");
        contentList.add("Wednesday");
        contentList.add("Thursday");
        contentList.add("Friday");
        contentList.add("Saturday");
        //Setting Multi Selection Spinner without image.
        multiSpinner.setAdapterWithOutImage(this,contentList,this);
        multiSpinner.initMultiSpinner(this,multiSpinner);
        OnMultiSpinnerItemSelected(contentList);


        //REPEAT SOUNDS spinner
        Spinner sound_spinner = (Spinner) findViewById(R.id.sound_spinner);
        ArrayAdapter<CharSequence> sound_adapter = ArrayAdapter.createFromResource(this,
                R.array.sound_list, android.R.layout.simple_spinner_item);
        sound_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sound_spinner.setAdapter(sound_adapter);



        //BUTTON SAVE
        save_button = findViewById(R.id.alarmSave);
        intent = new Intent(context,AlarmActivity.class);

        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String hourSelected = hour_spinner.getSelectedItem().toString();
                String minSelected = min_spinner.getSelectedItem().toString();
                String ampmSelected = amPm_spinner.getSelectedItem().toString();
                String daySelected = multiSpinner.getText().toString();

                String timeComb = hourSelected + ":" + minSelected;

                timeCard.add(timeComb);
                timeCard.add(ampmSelected);
                timeCard.add(daySelected);
                alarmInfo.add(timeCard);
                AlarmDataFileHelper.writeData(alarmInfo,getApplicationContext());
                //TODO: SHOULD BE LOOP into the file to add the list to alramInfo list
                adapter = new AlarmCardAdapter(alarmInfo,context);
                adapter.notifyDataSetChanged();


                context.startActivity(intent);
            }
        });


        //BUTTON DELETE ALRAM
        delete_button = findViewById(R.id.alram_delete_btn);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AlarmEditActivity.this, AlarmActivity.class);
                context.startActivity(i);
            }
        });



    }
    @Override
    //This is where you get all your items selected from the Multi Selection Spinner
    public void OnMultiSpinnerItemSelected(List<String> chosenItems) {

        ArrayList<String> weekday = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday", "Thursday", "Friday"));
        ArrayList<String> weekend = new ArrayList<>(Arrays.asList("Saturday","Sunday"));
        ArrayList<String> everyday = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday", "Thursday", "Friday","Saturday","Sunday"));
        ArrayList<String> selectedDay = new ArrayList<>();

        chosenItems.remove("");
        if (multiSpinner != null){

            StringBuilder stringBuilder = new StringBuilder();

            for(int i=0;i<chosenItems.size();i++){

                if(i+1 != chosenItems.size()){
                    selectedDay.add(chosenItems.get(i));
                    stringBuilder.append(chosenItems.get(i));
                    stringBuilder.append(",");
                } else {

                    stringBuilder.append(chosenItems.get(i));
                    selectedDay.add(chosenItems.get(i));
                }
            }

            Collections.sort(weekday);
            Collections.sort(weekend);
            Collections.sort(everyday);
            Collections.sort(selectedDay);



            if(selectedDay.equals(weekday)){
                multiSpinner.setText("Weekday");

            }
            else if(selectedDay.equals(weekend)){
                multiSpinner.setText("Weekend");
            }
            else if(selectedDay.equals(everyday)){
                multiSpinner.setText("Everyday");
            }
            else
                multiSpinner.setText(stringBuilder.toString());
        }




    }

}