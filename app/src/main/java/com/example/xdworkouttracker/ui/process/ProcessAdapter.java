package com.example.xdworkouttracker.ui.process;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xdworkouttracker.R;
import java.util.ArrayList;
import java.util.List;


public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.MyViewHolder> {

    private List<Process> list;
    private ItemClickListener clickListener;

    public ProcessAdapter(List<Process> list
//                          ItemClickListener clickListener
    ) {
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProcessAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_process, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.MyViewHolder holder, int position) {
        holder.processNo.setText(list.get(position).getProcessNo());
        holder.activity.setText(list.get(position).getActivity());
        holder.activitydate.setText(list.get(position).getDate());
        // click method for display detail of each Process
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    clickListener.onItemClick(list.get(holder.getAdapterPosition()));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView processNo;
        TextView activity;
        TextView activitydate;
        public MyViewHolder(View view){
            super(view);
            processNo = (TextView) view.findViewById(R.id.processNo);
            activity = (TextView) view.findViewById(R.id.process_activity_result);
            activitydate = (TextView) view.findViewById(R.id.process_activity_date_display);
        }
    }

    public interface  ItemClickListener {
        public void onItemClick(Process process);
    }
}
