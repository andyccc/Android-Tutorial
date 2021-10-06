package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordDatabase wordDatabase;
    WordDao wordDao;

    TextView textView;
    Button insertBtn, updateBtn, clearBtn, deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        insertBtn = findViewById(R.id.buttonInsert);
        updateBtn = findViewById(R.id.buttonUpdate);
        clearBtn = findViewById(R.id.buttonClear);
        deleteBtn = findViewById(R.id.buttonDelete);

        wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "wordDatabase")
                .allowMainThreadQueries()// 强制主线程执行，仅仅测试使用
                .build();
        wordDao = wordDatabase.getWordDao();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w1 = new Word("hello", "你好");
                Word w2 = new Word("word", "世界");

                wordDao.insertWords(w1, w2);
                updateView();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w = new Word("Hi", "你好啊");
                w.setId(25);
                wordDao.updateWords(w);
                updateView();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordDao.deleteAllWords();
                updateView();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w = new Word();
                w.setId(26);
                wordDao.deleteWords(w);
                updateView();
            }
        });

        updateView();

    }

    void updateView() {
        List<Word> list = wordDao.getAllWords();
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            Word w = list.get(i);
            text += w.getId() + ":" + w.getWord() + " = " + w.getChineseMeaning() + "\n";
        }
        textView.setText(text);

    }
}