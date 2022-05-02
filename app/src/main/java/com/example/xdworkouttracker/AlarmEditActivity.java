package com.example.xdworkouttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Application;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog;
import com.anurag.multiselectionspinner.MultiSpinner;
import com.example.xdworkouttracker.WebServices.ActForAlertFragmentActivity;
import com.example.xdworkouttracker.ui.alert.AlertFragment;
import com.example.xdworkouttracker.ui.alert.AlertViewModel;
import com.example.xdworkouttracker.ui.process.ProcessFragment;

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

    private ArrayList<AlertModel> timeCard;
    private AlarmCardAdapter adapter;


    MultiSpinner multiSpinner;
    Intent intent;

    Context context;

    private AlertViewModel avm;
    public AlertModel alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);


        timeCard = new ArrayList<>();
        alert = new AlertModel();

//        context = AlarmEditActivity.this;
//        avm = new ViewModelProvider(this).get(AlertViewModel.class);
//        avm.getAlertList().observe(this, new Observer<ArrayList<AlertModel>>() {
//            @Override
//            public void onChanged(ArrayList<AlertModel> alertModels) {
//                //update recycleView
//                Toast.makeText(AlarmEditActivity.this, "Doing on changed??????/" , Toast.LENGTH_LONG).show();
//            }
//        });

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
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String hourSelected = hour_spinner.getSelectedItem().toString();
                String minSelected = min_spinner.getSelectedItem().toString();
                String ampmSelected = amPm_spinner.getSelectedItem().toString();
                String daySelected = multiSpinner.getText().toString();

                String timeComb = hourSelected + ":" + minSelected;

                alert.setTime(timeComb);
                alert.setAmpm(ampmSelected);
                alert.setRepeatPattern(daySelected);

                AlarmDataFileHelper.addAlarm(alert);
                intent = new Intent(AlarmEditActivity.this, ActForAlertFragmentActivity.class);
                startActivity(intent);
                finish();
            }



        });


        //BUTTON Back
        delete_button = findViewById(R.id.alram_delete_btn);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlarmEditActivity.this, ActForAlertFragmentActivity.class);
                (AlarmEditActivity.this).startActivity(i);
                finish();
            } });

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