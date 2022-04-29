package com.example.xdworkouttracker.ui.alert;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.xdworkouttracker.AlarmCardAdapter;
import com.example.xdworkouttracker.AlarmDataFileHelper;
import com.example.xdworkouttracker.AlertModel;

import java.util.ArrayList;
import java.util.Arrays;

public class AlertViewModel extends AndroidViewModel{

    private MutableLiveData<ArrayList<AlertModel>> mAlertList;

    public ArrayList<AlertModel> alarmList;
    public AlarmCardAdapter acp;

    public AlertViewModel(@NonNull Application application) {
        super(application);
            alarmList = AlarmDataFileHelper.getAlarmStorage();
            mAlertList = new MutableLiveData<>();
    }

    //set the values in
    public void addAlert(AlertModel alert){
        //AlarmDataFileHelper.addAlarm(alert);
        alarmList.add(alert);
        mAlertList.setValue(alarmList);
    }

    public void deleteAlert(AlertModel alert){
      //  AlarmDataFileHelper.deleteAlarm(alert);
        alarmList.remove(alert);
        mAlertList.setValue(alarmList);

    }

    public LiveData<ArrayList<AlertModel>> getAlertList() {
        mAlertList.setValue(alarmList);
        System.out.println("in the view model:  " +mAlertList.getValue().toString());
        return mAlertList;
    }


}