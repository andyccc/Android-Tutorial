package com.example.viewmodelsp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    private String key = getApplication().getResources().getString(R.string.data_key);
    private String spName = getApplication().getResources().getString(R.string.my_sp_name);

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;

        if (!handle.contains(key)) {
            load();
        }

    }

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }

    private void load() {
        SharedPreferences sp = getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        int x = sp.getInt(key, 0);
        handle.set(key, x);
    }

    void save() {
        SharedPreferences sp = getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, getNumber().getValue());
        editor.apply();
    }

    public void add(int x) {
        handle.set(key, getNumber().getValue() + x);
        save();
    }
}
