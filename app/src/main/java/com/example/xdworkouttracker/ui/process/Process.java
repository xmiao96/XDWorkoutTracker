package com.example.xdworkouttracker.ui.process;

public class Process {


    private String processNo;
    private String duration;
    private String activity;
    private String result;
    private String carolies;


    public Process(String processNo, String duration, String activity, String result, String carolies) {
        this.processNo = processNo;
        this.duration = duration;
        this.activity = activity;
        this.result = result;
        this.carolies = carolies;
    }

    public Process (){}
    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCarolies() {
        return carolies;
    }

    public void setCarolies(String carolies) {
        this.carolies = carolies;
    }
}
