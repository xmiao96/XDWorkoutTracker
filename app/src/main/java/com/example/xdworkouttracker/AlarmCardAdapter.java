package com.example.xdworkouttracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xdworkouttracker.ui.alert.AlertFragment;
import com.example.xdworkouttracker.ui.alert.AlertViewModel;

import java.util.ArrayList;

public class AlarmCardAdapter extends RecyclerView.Adapter<AlarmCardAdapter.AlarmViewHolder>{


    //[time, ampm, day]
    public ArrayList<AlertModel> alarmInfo;
    public AlertViewModel avm;
    public Context context;

    public AlarmCardAdapter(){}

    public AlarmCardAdapter(ArrayList<AlertModel> alarmInfo, Context context) {
        this.alarmInfo = alarmInfo;
        this.context = context;
        avm = new ViewModelProvider((FragmentActivity) context).get(AlertViewModel.class);
        alarmInfo = new ArrayList<>();
    }


    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_card, parent, false);

        return new AlarmViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        AlertModel currentAlert = alarmInfo.get(position);
        (holder).time.setText(currentAlert.getTimetime());
        (holder).ampm.setText(currentAlert.getAmpm());
        (holder).day.setText(currentAlert.getRepeatPattern());
        (holder).alarmCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogMessage(currentAlert,context);
            }
        });


    }

    @Override
    public int getItemCount() {
        return alarmInfo.size();
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder{

        private TextView time;
        private TextView ampm;
        private TextView day;
        private CardView alarmCard;


        public AlarmViewHolder(@NonNull View itemView) {
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

    public void showDialogMessage(AlertModel currentAlert, Context context){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        AlertDialog show = alertDialog.setTitle("Delete Alarm")
                .setMessage("Do you want to delete this alarm?")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int clickPosition) {
                        if(alarmInfo !=null)
                            alarmInfo.remove(currentAlert);
                        setAlarmInfo(alarmInfo);

                    }
                })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Todo: finish the function to naviagate another page to show more information
                        dialog.cancel();

                    }
                }).show();

        alertDialog.create();
    }



    public void setAlarmInfo(ArrayList<AlertModel> alarmInfo){
        this.alarmInfo = alarmInfo;
        notifyDataSetChanged();
    }

}