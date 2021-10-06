package com.example.pagingdemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentsDatabase extends RoomDatabase {

    private  static StudentsDatabase instance;
    static synchronized StudentsDatabase getInstance(Context context) {
        if (null == instance) {
            instance = Room.databaseBuilder(context, StudentsDatabase.class, "student_database")
                    .build();
        }
        return instance;
    }

    abstract StudentDao getStudentDao();


}

