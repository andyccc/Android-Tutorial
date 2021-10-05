package com.example.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String TAG = "mytag";
    Button btn1;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        textView = findViewById(R.id.textView);

        if (null != savedInstanceState) {
            String title = savedInstanceState.getString("key");
            if (!TextUtils.isEmpty(title)) {
                textView.setText(title);
            }
            Log.d(TAG, "title: "+ title);
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.btn1);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
        String title = textView.getText().toString();
        // 屏幕旋转时会导致 activity 进行 onDestroy
        // 视图销毁时数据状态保存处理
        outState.putString("key", title);
        Log.d(TAG, "onSaveInstanceState: "+ title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}