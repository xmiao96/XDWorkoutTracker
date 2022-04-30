package com.example.xdworkouttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.xdworkouttracker.WebServices.ActForAlertFragmentActivity;
import com.example.xdworkouttracker.WebServices.ActForProcessFragmentActivity;
import com.example.xdworkouttracker.ui.process.Process;
import com.example.xdworkouttracker.ui.process.ProcessAdapter;
import com.example.xdworkouttracker.ui.process.ProcessFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class progress extends AppCompatActivity {

    public TextView time;
    public TextView weight;
    public TextView caroliesBurned;
    public TextView show_result;
    double MET = 3.5;
    public Button calculate;
    public Context context;
    public Spinner spinner;

    private ProcessAdapter adapter;

    private Button button;

    public Process process;
    public TextView actt;
    public TextView durr;
    public TextView shress;
    public Intent intent;

    public static String act,dur, shres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        context = this;
        spinner = findViewById(R.id.spinner_activities);
        time = findViewById(R.id.numberInput);
        weight = findViewById(R.id.weightInput);
        calculate = findViewById(R.id.calculate_btn);
        show_result = findViewById(R.id.calculation_result);
        button = findViewById(R.id.save_btn);

        process = new Process();
        actt = (TextView)findViewById(R.id.process_activity_result);
        durr = (TextView) findViewById(R.id.process_duration_result);
        shress = (TextView) findViewById(R.id.process_activity_result_display);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                act = spinner.getSelectedItem().toString();
                dur = time.getText().toString();
                shres = show_result.getText().toString();

                process.setActivities(act);
                process.setDuration(dur);
                process.setResult(shres);


                intent = new Intent(progress.this, ActForProcessFragmentActivity.class);
                intent.putExtra("Activity", act);
                intent.putExtra("Duration", dur);
                intent.putExtra("Result", shres);

                startActivity(intent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!time.getText().toString().isEmpty()&& !weight.getText().toString().isEmpty()) {
                    double time_int = Double.parseDouble(time.getText().toString());
                    time_int /= 60;
                    double weight_int = Double.parseDouble(weight.getText().toString());
                    double result = (double) (time_int * weight_int * MET / 200) * 200;

                    String result_s = result + "";
                    show_result.setText(result_s);
                }
                else{
                    show_result.setText("Please enter exercise duration and current weight!");
                }
            }
        });
    }
}