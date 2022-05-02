package com.example.xdworkouttracker.ui.process;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xdworkouttracker.R;

import java.util.List;


public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.MyViewHolder> {

    private List<Process> list;
    private ItemClickListener clickListener;

    public Context context;
    public ProcessAdapter(){}

    public ProcessAdapter(List<Process> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ProcessAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_process, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.MyViewHolder holder, int position) {
        holder.activity.setText(list.get(position).getActivities());
        holder.duration.setText(list.get(position).getDuration());
        holder.cal_result.setText(list.get(position).getResult());
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
        TextView duration;
        TextView activity;
        TextView cal_result;
        public MyViewHolder(View view){
            super(view);
            activity = (TextView) view.findViewById(R.id.process_activity_result);
            duration = (TextView) view.findViewById(R.id.process_duration_result);
            cal_result = (TextView) view.findViewById(R.id.process_activity_result_display);
        }
    }

    public interface  ItemClickListener {
        public void onItemClick(Process process);
    }
}
