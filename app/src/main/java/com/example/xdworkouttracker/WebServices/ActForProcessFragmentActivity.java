package com.example.xdworkouttracker.WebServices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.ui.process.Process;

public class ActForProcessFragmentActivity extends AppCompatActivity {


    public Process process;
    public String aaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_for_process_fragment);
    }
}