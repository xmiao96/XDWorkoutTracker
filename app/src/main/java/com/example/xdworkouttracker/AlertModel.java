package com.example.xdworkouttracker;

public class AlertModel {

    private String time;
    private String ampm;
    private String repeatPattern;

    public AlertModel(String time, String ampm, String repeatPattern) {
        this.time = time;
        this.ampm = ampm;
        this.repeatPattern = repeatPattern;
    }

    public AlertModel(){

    }

    public String getTimetime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getRepeatPattern() {
        return repeatPattern;
    }

    public void setRepeatPattern(String repeatPattern) {
        this.repeatPattern = repeatPattern;
    }
}
