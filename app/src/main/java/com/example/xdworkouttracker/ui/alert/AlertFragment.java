package com.example.xdworkouttracker.ui.alert;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.AlarmCardAdapter;
import com.example.xdworkouttracker.AlarmDataFileHelper;
import com.example.xdworkouttracker.AlarmEditActivity;
import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.databinding.FragmentAlertBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class AlertFragment extends Fragment {

    private FragmentAlertBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<String> timeCard = new ArrayList<>();
    public ArrayList<ArrayList<String>> alarmInfo;
    private AlarmCardAdapter adapter;
    public Intent intent;
    public Intent intentSetUpAlarm;

    public Button setUpAlarm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_alarm, container, false);


        recyclerView = view.findViewById(R.id.alarm_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //get alarm list data from file
        alarmInfo = new ArrayList<>();
        alarmInfo = AlarmDataFileHelper.readData(view.getContext());

        System.out.println(alarmInfo);

        adapter = new AlarmCardAdapter(alarmInfo,getActivity());
        recyclerView.setAdapter(adapter);

        if(recyclerView.getParent() !=null){
            ((ViewGroup)recyclerView.getParent()).removeView(recyclerView);
        }

        setUpAlarm = view.findViewById(R.id.setUpBtn);

        setUpAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSetUpAlarm = new Intent(getActivity(), AlarmEditActivity.class);
                getActivity().startActivity(intentSetUpAlarm);


            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}