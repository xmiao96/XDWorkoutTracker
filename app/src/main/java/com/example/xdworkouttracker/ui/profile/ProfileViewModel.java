package com.example.xdworkouttracker.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.xdworkouttracker.UserServices.User;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}