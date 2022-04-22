package com.example.xdworkouttracker;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AlarmDataFileHelper {

    public static final String FILENAME = "alarminfo.dat";


    public static void writeData(ArrayList<ArrayList<String>> alarm, Context context){
        try (
                FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)) {
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(alarm);
            oas.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static ArrayList<ArrayList<String>> readData(Context context){
        ArrayList<ArrayList<String>> alarmList = null;
        //ArrayList<String> alarmItem = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // alarmItem = (ArrayList<String>)ois.readObject();
            // alarmList.add(alarmItem);
            alarmList = (ArrayList<ArrayList<String>>)ois.readObject();

        } catch (FileNotFoundException e) {
            alarmList = new ArrayList<>();
            // alarmItem = new ArrayList<>();
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return alarmList;


    }
}