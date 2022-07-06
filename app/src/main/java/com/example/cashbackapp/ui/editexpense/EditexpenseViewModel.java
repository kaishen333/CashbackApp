package com.example.cashbackapp.ui.editexpense;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditexpenseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EditexpenseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is edit expense fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}