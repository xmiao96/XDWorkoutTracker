package com.example.xdworkouttracker.UserServices;

public class User {
    private String username;
    private String password;
    private float weight;
    private float height;
    private boolean gender;

    public void User(String username, String password, float weight, float height, boolean gender){
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public boolean isMale() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

