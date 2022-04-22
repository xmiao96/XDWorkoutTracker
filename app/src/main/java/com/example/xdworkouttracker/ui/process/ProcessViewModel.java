package com.example.xdworkouttracker.ui.process;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProcessViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public ProcessViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
