package com.example.serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView textViewGrade;
    private Button buttonSave, buttonLoad;
    private EditText editTextName, editTextAge, editTextMath, editTextEnglish, editTextChinese;

    private static final String FILE_NAME = "my.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGrade = findViewById(R.id.textViewGrade);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextMath = findViewById(R.id.editTextMath);
        editTextEnglish = findViewById(R.id.editTextEnglish);
        editTextChinese = findViewById(R.id.editTextChinese);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int math = Integer.valueOf(editTextMath.getText().toString());
                int english = Integer.valueOf(editTextEnglish.getText().toString());
                int chinese = Integer.valueOf(editTextChinese.getText().toString());

                Score score = new Score(math, english, chinese);
                String name = editTextName.getText().toString();
                int age = Integer.valueOf(editTextAge.getText().toString());
                Student student = new Student(name, age, score);

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(FILE_NAME, MODE_PRIVATE));
                    oos.writeObject(student);
                    oos.flush();
                    oos.close();

                    Toast.makeText(MainActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();


                    editTextName.getText().clear();
                    textViewGrade.setText("-");
                    editTextAge.getText().clear();
                    editTextEnglish.getText().clear();
                    editTextChinese.getText().clear();
                    editTextMath.getText().clear();

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("errortag1", "onClick: ", e);
                }


            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(openFileInput(FILE_NAME));
                    Student student = (Student)ois.readObject();

                    editTextName.setText(student.getName());
                    textViewGrade.setText(student.getScore().getGrade());
                    editTextAge.setText(String.valueOf(student.getAge()));
                    editTextEnglish.setText(String.valueOf(student.getScore().getEnglish()));
                    editTextChinese.setText(String.valueOf(student.getScore().getChinese()));
                    editTextMath.setText(String.valueOf(student.getScore().getMath()));

                    Toast.makeText(MainActivity.this, "Data Loaded!", Toast.LENGTH_SHORT).show();


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Log.d("errortag2", "onClick: ", e);
                }

            }
        });


    }
}