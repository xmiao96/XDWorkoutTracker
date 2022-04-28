package com.example.xdworkouttracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.ui.alert.AlertFragment;

import java.util.ArrayList;

public class AlarmCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    //[time, ampm, day]
    public ArrayList<ArrayList<String>> alarmInfo;
    public Context context;

    public AlarmCardAdapter(ArrayList<ArrayList<String>> alarmInfo, Context context) {
        this.alarmInfo = alarmInfo;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_card, parent, false);

        return new alarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((alarmViewHolder)holder).time.setText(alarmInfo.get(position).get(0));
        ((alarmViewHolder)holder).ampm.setText(alarmInfo.get(position).get(1));
        ((alarmViewHolder)holder).day.setText(alarmInfo.get(position).get(2));
        ((alarmViewHolder)holder).alarmCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMessage(position, context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alarmInfo.size();
    }

    public class alarmViewHolder extends RecyclerView.ViewHolder{

        private TextView time;
        private TextView ampm;
        private TextView day;
        private CardView alarmCard;


        public alarmViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.display_time);
            ampm = itemView.findViewById(R.id.display_ampm);
            day = itemView.findViewById(R.id.display_day);
            alarmCard = itemView.findViewById(R.id.card_design_alram);

        }

        public CardView getView(){
            return alarmCard;
        }
    }

    public void showDialogMessage(int clickPosition, Context context){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        AlertDialog show = alertDialog.setTitle("Edit Alarm")
                .setMessage("Do you want to edit this alarm?")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(alarmInfo !=null)
                            alarmInfo.remove(clickPosition);
                    }
                })
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Todo: finish the function to naviagate another page to show more information
                        Intent intent = new Intent(context, AlarmEditActivity.class);

                        // intent.putExtra("alram", alarmInfo.get(clickPosition));

                        // intent.putExtra("cakeId", clickPosition);

                        context.startActivity(intent);

                    }
                }).show();

        alertDialog.create();
    }
}