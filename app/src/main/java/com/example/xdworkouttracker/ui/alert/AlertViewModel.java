package com.example.xdworkouttracker.ui.alert;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlertViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlertViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ALERT fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}