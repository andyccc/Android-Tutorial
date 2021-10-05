package com.example.sharedprefernce;

import android.content.Context;
import android.content.SharedPreferences;

public class MyData {

    public  int number;
    private Context context;

    public MyData(Context context) {
        this.context = context;
    }


    public void save() {
        String name = context.getResources().getString(R.string.my_data);
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String key = context.getResources().getString(R.string.my_data_number);
        editor.putInt(key, number);
//        editor.commit();
        editor.apply();// 非同步操作
    }

    public int load() {
        String name = context.getResources().getString(R.string.my_data);
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String key = context.getResources().getString(R.string.my_data_number);
        return sp.getInt(key, 0 );
    }

}
