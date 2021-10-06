package com.example.viewmodelsavedstate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private SavedStateHandle handle;
    private static final String KEY_NUMBER = "KEY_NUMBER";

    public MyViewModel(SavedStateHandle handle) {
        if (!handle.contains(KEY_NUMBER)) {
            handle.set(KEY_NUMBER, 0);
        }
        this.handle = handle;
    }

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(KEY_NUMBER);
    }

    public void add() {
        handle.set(KEY_NUMBER, (int)handle.get(KEY_NUMBER) + 1);
    }

}
