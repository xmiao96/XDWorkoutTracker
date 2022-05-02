package com.example.xdworkouttracker.WebServices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.AlarmCardAdapter;
import com.example.xdworkouttracker.AlarmDataFileHelper;
import com.example.xdworkouttracker.AlarmEditActivity;
import com.example.xdworkouttracker.MainActivity;
import com.example.xdworkouttracker.R;

import java.util.ArrayList;

public class ActForAlertFragmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_alert_fragment);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}