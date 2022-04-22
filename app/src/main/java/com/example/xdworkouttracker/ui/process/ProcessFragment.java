package com.example.xdworkouttracker.ui.process;

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

import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.progress;
//import com.example.xdworkouttracker.databinding.FragmentProcessBinding;

import java.util.ArrayList;


public class ProcessFragment extends Fragment {

    private Button button;
    private ArrayList<Process> arrayList = new ArrayList<>();
    public ProcessFragment(){}
    public String ac, du, ca, re;


//    private FragmentProcessBinding binding;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_process, container, false);
        button = (Button)view.findViewById(R.id.add_process);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), progress.class);
                startActivity(intent);
            }
        });
        arrayList.clear();
        buildListData();
        initRecycleView(view);
        return view;
    }
//
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            ac = getArguments().getString("Activity");
//            du = getArguments().getString("Duration");
//            ca = getArguments().getString("Carolies");
//            re = getArguments().getString("Result");
            Toast.makeText(getActivity(), "test" + ac, Toast.LENGTH_SHORT).show();
        }
//        arrayList.add(new Process("1","Running","45","456","3.2"));
    }

    private void initRecycleView (View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ProcessAdapter adapter = new ProcessAdapter(arrayList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


    private void buildListData() {
        arrayList.add(new Process("1","45","Running","456","3.2"));
        arrayList.add(new Process("2","45","walking","456","3.2"));

//        Bundle bundle = getArguments();
//
//        if(getArguments() != null){
//            ac = getArguments().getString("Activity");
//            du = getArguments().getString("Duration");
//            ca = getArguments().getString("Carolies");
//            re = getArguments().getString("Result");
//            arrayList.add(new Process("1",du,ac,re, ca));
//        }
    }


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