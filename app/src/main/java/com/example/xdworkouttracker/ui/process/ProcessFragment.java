package com.example.xdworkouttracker.ui.process;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.AlarmDataFileHelper;
import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.progress;
//import com.example.xdworkouttracker.databinding.FragmentProcessBinding;

import java.util.ArrayList;


public class ProcessFragment extends Fragment {

    private Button button;
    public static ArrayList<Process> arrayList = new ArrayList<>();
    public ProcessFragment(){}
    public Process process;
    public ProcessAdapter adapter;
    public RecyclerView recyclerView;
    public Intent intent;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_process, container, false);
        button = (Button)view.findViewById(R.id.add_process);


        process = new Process();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), progress.class);
                startActivity(intent);
            }
        });

        System.out.println("------------->" + getActivity().getIntent().getStringExtra("Duration"));

        if(AlarmDataFileHelper.getProcessStorage().size() > 0){
            intent = getActivity().getIntent();
            String ddd = intent.getStringExtra("Duration");
            String aaa = intent.getStringExtra("Activity");
            String rrr = intent.getStringExtra("Result");
            process = new Process(ddd,aaa,rrr);
        }

        AlarmDataFileHelper.addProcess(process);
        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProcessAdapter(AlarmDataFileHelper.getProcessStorage());
        recyclerView.setAdapter(adapter);
//        buildListData();
//        initRecycleView(view);
        return view;
    }



//    private void initRecycleView (View view){
//        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        ProcessAdapter adapter = new ProcessAdapter(arrayList);
//        adapter.notifyDataSetChanged();
//        recyclerView.setAdapter(adapter);
//    }

//    public void buildListData() {
//        String ddd = process.getDuration();
//        String aaa = process.getActivities();
//        String rrr = process.getResult();
//
//        System.out.println("------------this is aaa" + aaa);
//
//        arrayList.add(new Process(ddd, aaa, rrr));
//
//
//    }


    // this method for displaying detail after click each Process in list view
//    @Override
//    public void onItemClick(Process process) {
//        Fragment fragment = DetailFragment.newInstance(process.getActivity());
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.nav_view, fragment,"detail_fragment");
//        transaction.hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.titletv));
//        transaction.add(R.id.titletv, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}