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

    private Button button;

    private ArrayList<Process> list;
    private ProcessAdapter adapter;
    private  RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        context = this;
        spinner = findViewById(R.id.spinner_activities);
        time = findViewById(R.id.numberInput);
        weight = findViewById(R.id.weightInput);
        calculate = findViewById(R.id.calculate_btn);
        caroliesBurned = findViewById(R.id.carolies_title);
        show_result = findViewById(R.id.calculation_result);
        button = findViewById(R.id.save_btn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Bundle bundle = new Bundle();
              bundle.putString("Activity", spinner.getSelectedItem().toString());
                bundle.putString("Duration", time.getText().toString());
                bundle.putString("Carolies", caroliesBurned.getText().toString());
                bundle.putString("Result", show_result.getText().toString());

                ProcessFragment processFragment = new ProcessFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                processFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, processFragment)
                        .addToBackStack(null).commit();

                System.out.println("bundle" + bundle);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double time_int = Double.parseDouble(time.getText().toString());
                time_int /=60;
                double weight_int = Double.parseDouble(weight.getText().toString());
                double result = (double) (time_int * weight_int * MET / 200);

                String result_s = result +"";
                show_result.setText(result_s);
            }
        });
    }
}