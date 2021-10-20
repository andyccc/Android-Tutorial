package com.example.viewbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.viewbinding.databinding.ActivityJmainBinding;

public class JMainActivity extends AppCompatActivity {

    private ActivityJmainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // old
//        setContentView(R.layout.activity_jmain);
//        Button btn1 = findViewById(R.id.button1);
//        Button btn2 = findViewById(R.id.button2);

        // new
        binding = ActivityJmainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView1.setText("1");
        binding.textView2.setText("2");

    }

}
