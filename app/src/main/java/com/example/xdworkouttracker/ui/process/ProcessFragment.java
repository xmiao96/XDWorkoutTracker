package com.example.xdworkouttracker.ui.process;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.R;
//import com.example.xdworkouttracker.databinding.FragmentProcessBinding;

import java.util.ArrayList;

public class ProcessFragment extends Fragment {

    private ArrayList<Process> arrayList = new ArrayList<>();
    public ProcessFragment(){}

//    private FragmentProcessBinding binding;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_process, container, false);
        arrayList.clear();
        buildListData();
        initRecycleView(view);
        return view;
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
        arrayList.add(new Process( "1","Swimming", "02/02/2022"));
        arrayList.add(new Process("2","Running","02/21/2022"));
        arrayList.add(new Process("3","Walking", "03/10/2022"));
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