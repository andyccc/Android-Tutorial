package com.example.viewmodelrestore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    final static String KEY_NUMBER ="key_number";

//    private MutableLiveData<Integer> number;
    private SavedStateHandle handle;

    public MyViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber() {
        if (!handle.contains(KEY_NUMBER)) {
            handle.set(KEY_NUMBER, 0);
        }

        return handle.getLiveData(KEY_NUMBER);
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }
}
