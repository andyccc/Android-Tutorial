package com.example.calculationtest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {

    private SavedStateHandle handle;

    private final String KEY_HIGH_SCORE = "KEY_HIGH_SCORE";
    private final String KEY_LEFT_NUMBER = "KEY_LEFT_NUMBER";
    private final String KEY_RIGHT_NUMBER = "KEY_RIGHT_NUMBER";
    private final String KEY_OPERATOR = "KEY_OPERATOR";
    private final String KEY_ANSWER = "KEY_ANSWER";
    private final String KEY_CURRENT_SCORE = "KEY_CURRENT_SCORE";

    private final String SP_DATA_NAME = "KEY_SP_DATA_NAME";

    boolean winflag = false;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);

        if (!handle.contains(KEY_HIGH_SCORE)) {
            SharedPreferences sp = getApplication().getSharedPreferences(SP_DATA_NAME, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE, sp.getInt(KEY_HIGH_SCORE, 0));
            handle.set(KEY_LEFT_NUMBER, 0);
            handle.set(KEY_RIGHT_NUMBER, 0);
            handle.set(KEY_OPERATOR, "+");
            handle.set(KEY_ANSWER, 0);
            handle.set(KEY_CURRENT_SCORE, 0);
        }

        this.handle = handle;
    }

    public MutableLiveData<Integer> getLeftNumber() {
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }

    public MutableLiveData<Integer> getRightNumber() {
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }

    public MutableLiveData<String> getOperator() {
        return handle.getLiveData(KEY_OPERATOR);
    }

    public MutableLiveData<Integer> getHighScore() {
        return handle.getLiveData(KEY_HIGH_SCORE);
    }

    public MutableLiveData<Integer> getCurrentScore() {
        return handle.getLiveData(KEY_CURRENT_SCORE);
    }

    public MutableLiveData<Integer> getAnswer() {
        return handle.getLiveData(KEY_ANSWER);
    }

    public void generator() {
        int LEVEL = 20;
        Random random = new Random();
        int x, y;
        x = random.nextInt(LEVEL + 1);
        y = random.nextInt(LEVEL + 1);
        if (x % 2 == 0) {
            getOperator().setValue("+");
            if (x > y) {
                getAnswer().setValue(x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x - y);
            } else {
                getAnswer().setValue(y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y - x);
            }
        } else {
            getOperator().setValue("-");
            if (x > y) {
                getAnswer().setValue(x - y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
            } else {
                getAnswer().setValue(y - x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
            }

        }
    }

    public void save() {
        SharedPreferences sp = getApplication().getSharedPreferences(SP_DATA_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //noinspection ConstantConditions
        editor.putInt(KEY_HIGH_SCORE, getHighScore().getValue());
        editor.apply();
    }

    public void answerCorrect() {
        getCurrentScore().setValue(getCurrentScore().getValue() + 1);
        if (getCurrentScore().getValue() > getHighScore().getValue()) {
            getHighScore().setValue(getCurrentScore().getValue());
            // 取得了新的记录
            winflag = true;
        }
        // 生成新题
        generator();
    }

}
