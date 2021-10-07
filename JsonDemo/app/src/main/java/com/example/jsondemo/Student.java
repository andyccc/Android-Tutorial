package com.example.jsondemo;

import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("name_1")
    String name;
    int age;
    Score score;
    transient
    int foo;// transient 这个字段不会被json序列化

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Student(String name, int age, Score score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
