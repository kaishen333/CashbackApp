package com.example.cashbackapp.ui.addexpense;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddexpenseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddexpenseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add expense fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}