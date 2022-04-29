package com.example.xdworkouttracker.ui.process;

public class Process {

    private String duration = "45";
    private String activities = "Running";
    private String result = "320";


    public Process(String duration, String activities, String result) {
        this.duration = duration;
        this.activities = activities;
        this.result = result;

    }

    public Process (){}


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
