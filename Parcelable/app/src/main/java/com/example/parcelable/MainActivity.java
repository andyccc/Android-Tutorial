package com.example.parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcelable.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int math = Integer.valueOf(binding.editTextMath.getText().toString());
                int english = Integer.valueOf(binding.editTextEnglish.getText().toString());

                Score score = new Score(math, english);
                String name = binding.editTextName.getText().toString();
                int age = Integer.valueOf(binding.editTextAge.getText().toString());

                Student student = new Student(name, age, score);
                sendData1(student);
//                sendData2(student);

            }
        });

    }

    void sendData1(Student student) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("student", student);

        // 方式1
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//      intent.putExtra("student", student);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    void sendData2(Student student) {
        // 方式2
        MyApplication application = (MyApplication) getApplication();
        application.student = student;
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }


}