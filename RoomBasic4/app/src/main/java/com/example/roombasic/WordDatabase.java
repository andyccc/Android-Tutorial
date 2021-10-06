package com.example.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Word.class}, version = 4, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context) {
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "wordDatabase")
//                    .fallbackToDestructiveMigration() // 破坏式 清空所有数据，更新表结构
//                    .allowMainThreadQueries() // 允许在主线程执行
                    .addMigrations(MIGRATION_3_4)
                    .build();

        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

    // 字段增加
    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE WORD ADD COLUMN BAR_DATA INTEGER NOT NULL DEFAULT 1");
            database.execSQL("ALTER TABLE WORD ADD COLUMN FOO_DATA INTEGER NOT NULL DEFAULT 1");
        }
    };

    // 字段减少
    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE WORD_TEMP (id INTEGER PRIMARY KEY NOT NULL, english_word TEXT, chinese_meaning TEXT)");
            database.execSQL("INSERT INTO WORD_TEMP (id, english_word, chinese_meaning)" + "" +
                    "SELECT id, english_word, chinese_meaning FROM WORD");
            database.execSQL("DROP TABLE WORD");
            database.execSQL("ALTER TABLE WORD_TEMP RENAME TO WORD");
        }
    };

}
