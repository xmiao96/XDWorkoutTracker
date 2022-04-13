package com.example.xdworkouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class progress extends AppCompatActivity {

    public TextView time;
    public TextView weight;
    public TextView show_result;
    double MET = 3.5;
    public Button calculate;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        context = this;
        time = findViewById(R.id.numberInput);
        weight = findViewById(R.id.weightInput);
        calculate = findViewById(R.id.calculate_btn);
        show_result = findViewById(R.id.calculation_result);

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