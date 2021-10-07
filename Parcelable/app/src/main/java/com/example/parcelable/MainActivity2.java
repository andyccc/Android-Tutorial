package com.example.parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.parcelable.databinding.ActivityMain2Binding;
import com.example.parcelable.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMain2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        Student student = getData1();
//        Student student = getData2();


        binding.editTextName.setText(student.getName());
        binding.editTextAge.setText(String.valueOf(student.getAge()));
        binding.editTextEnglish.setText(String.valueOf(student.getScore().getEnglish()));
        binding.editTextMath.setText(String.valueOf(student.getScore().getMath()));

    }

    Student getData1() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        Student student = bundle.getParcelable("student");
        return student;
    }

    Student getData2() {
        MyApplication application = (MyApplication)getApplication();
        return application.student;
    }

}