package com.example.xdworkouttracker;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AlarmDataFileHelper {

    public static final String FILENAME = "alarminfo.dat";

    public static ArrayList<AlertModel> alarmStorage = new ArrayList<>();


    public static void addAlarm(AlertModel alertModel){
        alarmStorage.add(alertModel);
    }

    public static void deleteAlarm(AlertModel alertModel){
        alarmStorage.remove(alertModel);
    }

    public static ArrayList<AlertModel> getAlarmStorage(){
        return alarmStorage;
    }



}