package com.example.sharedprefernce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String TAG = "mytag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test2();
    }

    public void test2() {
        MyData mydata = new MyData(getApplicationContext());
        mydata.number = 100;
        mydata.save();
        int x = mydata.load();
        Log.d(TAG, "onCreate: "+ x);

    }
    public void test1() {

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences sp = getSharedPreferences("main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("number", 100);
//        editor.commit();
        editor.apply();// 非同步操作

        int x = sp.getInt("number", 0);
        Log.d(TAG, "onCreate: "+ x);
    }
}