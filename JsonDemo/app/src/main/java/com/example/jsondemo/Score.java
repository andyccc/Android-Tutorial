package com.example.jsondemo;

public class Score {

    int math;
            int english;
            int chinese;

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

    public Score(int math, int english, int chinese) {
        this.math = math;
        this.english = english;
        this.chinese = chinese;
    }
}
