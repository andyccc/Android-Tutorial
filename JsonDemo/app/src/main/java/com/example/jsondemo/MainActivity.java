package com.example.jsondemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testData4();
    }


    void testData4() {
        Student student1 = new Student("Jack", 20, new Score(1,2,3));
        Student student2 = new Student("Tom", 20, new Score(2,3,4));
        List<Student> list =  new ArrayList<>();
        list.add(student1);
        list.add(student2);


        Gson gson = new Gson();
        String json1 = gson.toJson(list);

        json1 = "[{\"foo\":1,\"age\":20,\"name\":\"Jack1\",\"score\":{\"chinese\":3,\"english\":2,\"math\":1}},{\"age\":20,\"name\":\"Tom1\",\"score\":{\"chinese\":4,\"english\":3,\"math\":2}}]";
        Type type = new TypeToken<List<Student>>(){}.getType();
        list = gson.fromJson(json1, type);

        if (json1!=null) {


        }

    }


    void testData3() {
        Student student1 = new Student("Jack", 20, new Score(1,2,3));
        Student student2 = new Student("Tom", 20, new Score(2,3,4));
        Student[] students = {student1, student2};
        Gson gson = new Gson();
        String json1 = gson.toJson(students);

        json1 = "[{\"age\":20,\"name\":\"Jack1\",\"score\":{\"chinese\":3,\"english\":2,\"math\":1}},{\"age\":20,\"name\":\"Tom1\",\"score\":{\"chinese\":4,\"english\":3,\"math\":2}}]";
        students = gson.fromJson(json1, Student[].class);

        List<Student> list = Arrays.asList(students);

        if (json1!=null) {


        }

    }

    void testData2() {

        Gson gson = new Gson();
        Score score =  new Score(1,2,3);
        Student student = new Student("Jack", 20, score);
        String json1 = gson.toJson(student);

        json1 = "{\"age\":20,\"name\":\"Jack\",\"score\":{\"chinese\":4,\"english\":2,\"math\":1}}";
        student = gson.fromJson(json1, Student.class);


        if (json1!=null) {

        }

    }


    void testData1() {
        Student student = new Student("Jack", 20, null);
        Gson gson = new Gson();
        String json1 = gson.toJson(student);

        json1 = "{\"age\":21,\"name\":\"Jack\"}";
        student = gson.fromJson(json1, Student.class);

        if (json1!=null) {

        }

    }
}