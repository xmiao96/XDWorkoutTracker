package com.example.xdworkouttracker.ui.process;

import java.util.Date;

public class Process {
    String activity, date;
    int processNo, carolies;

    public Process(int processNo, String activity, int carolies, String date) {
        this.processNo = processNo;
        this.activity = activity;
        this.carolies = carolies;
        this.date = date;
    }
}
