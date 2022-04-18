package com.example.xdworkouttracker.ui.process;

import java.util.Date;

public class Process {
    private String processNo, activity,d;


    public Process(
            String processNo,
            String activity,
            String d
    ) {
        this.processNo = processNo;
        this.activity = activity;
        this.d = d;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDate() {
        return d;
    }

    public void setDate(String date) {
        this.d = date;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

}
