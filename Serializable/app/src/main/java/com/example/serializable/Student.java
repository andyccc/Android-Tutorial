package com.example.serializable;


import java.io.Serializable;

public class Student implements Serializable {

    // 如果不设置serialVersionUID字段，类的结构发生改变是，对象读取则会报错
    /*
        java.io.InvalidClassException: com.example.serializable.Student; local class incompatible: stream classdesc serialVersionUID = -8449296755745138964, local class serialVersionUID = 3231324503065474954

    * */
    private static final long serialVersionUID = 3231324503065474954L;
    private String name;
    private int age;
    private Score score;
    int foo;

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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}

class Score implements  Serializable{
    private static final long serialVersionUID = 890150913833791039L;
    private int math;
    private int english;
    private int chinese;
    private String grade;

    // transient 标记的字段不会进行序列化
//    private transient String grade2;


    public Score(int math, int english, int chinese) {
        this.math = math;
        this.english = english;
        this.chinese = chinese;

        if (math >= 90 && english >=90 && chinese >= 90) {
            this.grade = "A";
        } else  if (math >= 80 && english >=80 && chinese >= 80) {
            this.grade = "B";
        } else {
            this.grade ="C";
        }
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}