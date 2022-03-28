package com.example.xdworkouttracker.ui.process;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xdworkouttracker.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Process> {
    public ListAdapter(Context context, ArrayList<Process> processArrayList){
        super(context, R.layout.list_process, processArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Process process = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_process, parent, false);
        }
        TextView processNo = convertView.findViewById(R.id.processNo);
        TextView caroliesResult = convertView.findViewById(R.id.carolies_result);
        TextView processActivity = convertView.findViewById(R.id.process_activity);
        TextView processDate = convertView.findViewById(R.id.process_activity_date);

        processNo.setText(process.processNo);
        caroliesResult.setText(process.carolies);
        processActivity.setText(process.activity);
        processDate.setText(process.date);
        return convertView;
    }
}
