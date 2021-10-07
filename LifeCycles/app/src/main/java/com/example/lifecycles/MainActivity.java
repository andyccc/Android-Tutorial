package com.example.lifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    String TAG = "mytag";

    MyChronometer chronometer;
    private long elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        getLifecycle().addObserver(chronometer);
    }

}