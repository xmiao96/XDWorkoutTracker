package com.example.xdworkouttracker.ui.alert;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.AlarmCardAdapter;
import com.example.xdworkouttracker.AlarmDataFileHelper;
import com.example.xdworkouttracker.AlarmEditActivity;
import com.example.xdworkouttracker.AlertModel;
import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.databinding.FragmentAlertBinding;

import java.util.ArrayList;


public class AlertFragment extends Fragment {

    private FragmentAlertBinding binding;
    private RecyclerView recyclerView;
    public ArrayList<ArrayList<String>> alarmInfo;
    private AlarmCardAdapter adapter;
    public Intent intent;
    public Intent intentSetUpAlarm;

    public Button setUpAlarm;
    public View view;
    public AlertViewModel avm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_alarm, container, false);
        super.onCreate(savedInstanceState);

        recyclerView = view.findViewById(R.id.alarm_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        if(recyclerView.getParent() !=null){
//            ((ViewGroup)recyclerView.getParent()).removeView(recyclerView);
//        }

        //adapter = new AlarmCardAdapter();
        adapter = new AlarmCardAdapter(AlarmDataFileHelper.getAlarmStorage(),getContext());
        recyclerView.setAdapter(adapter);

        avm = new ViewModelProvider(requireActivity()).get(AlertViewModel.class);
        avm.getAlertList().observe(getViewLifecycleOwner(), new Observer<ArrayList<AlertModel>>() {
            @Override
            public void onChanged(ArrayList<AlertModel> alertModels) {
                //update recycleView
                adapter.setAlarmInfo(alertModels);
                Toast.makeText(getContext(), "Alarm setting. " , Toast.LENGTH_LONG).show();
            }
        });



        setUpAlarm = view.findViewById(R.id.setUpBtn);

        setUpAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSetUpAlarm = new Intent(getActivity(), AlarmEditActivity.class);
                getActivity().startActivity(intentSetUpAlarm);
                getActivity().finish();
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