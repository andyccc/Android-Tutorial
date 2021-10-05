package com.example.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "mytag";

    TextView display;
    Button buttonLeft, buttonRight, buttonOk;
    Switch aSwitch;
    ProgressBar progressBar;
    EditText editText;
    RadioGroup radioGroup;
    ImageView imageView;
    SeekBar seekBar;
    CheckBox checkBoxyw, checkBoxsx, checkBoxyy;
    RatingBar ratingBar;

    String yuwen = "", shuxue = "", yinyu = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);
        buttonLeft = findViewById(R.id.button1);
        buttonRight = findViewById(R.id.button2);
        buttonOk = findViewById(R.id.button3);
        aSwitch = findViewById(R.id.switchBtn);
        progressBar = findViewById(R.id.progressBar3);
        editText = findViewById(R.id.editTextNumber);
        radioGroup = findViewById(R.id.radiogroup);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        checkBoxyw = findViewById(R.id.checkBox1);
        checkBoxsx = findViewById(R.id.checkBox2);
        checkBoxyy = findViewById(R.id.checkBox3);
        ratingBar = findViewById(R.id.ratingBar);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(R.string.button1);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(R.string.button2);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                display.setText(isChecked ? "开" : "关");
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();
                if (TextUtils.isEmpty(value)) {
                    value = "0";
                }

                progressBar.setProgress(Integer.valueOf(value));
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "onCheckedChanged: " + checkedId);
                if (checkedId == R.id.radioButton2) {
                    imageView.setImageResource(R.drawable.android);
                } else {
                    imageView.setImageResource(R.drawable.ios);
                }

                RadioButton btn = group.findViewById(checkedId);
                Toast.makeText(getApplicationContext(), btn.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                display.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        checkBoxyw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yuwen = isChecked ? "语文" : "";
                display.setText(yuwen + shuxue + yinyu);
            }
        });
        checkBoxsx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shuxue = isChecked ? "数学" : "";
                display.setText(yuwen + shuxue + yinyu);
            }
        });

        checkBoxyy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yinyu = isChecked ? "英语" : "";
                display.setText(yuwen + shuxue + yinyu);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });


    }


}