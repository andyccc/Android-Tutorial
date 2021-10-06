package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordViewModel wordViewModel;
    TextView textView;
    Button insertBtn, updateBtn, clearBtn, deleteBtn;
    Word lastWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        insertBtn = findViewById(R.id.buttonInsert);
        updateBtn = findViewById(R.id.buttonUpdate);
        clearBtn = findViewById(R.id.buttonClear);
        deleteBtn = findViewById(R.id.buttonDelete);

        wordViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(WordViewModel.class);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    Word w = words.get(i);
                    text.append(w.getId() + ":" + w.getWord() + " = " + w.getChineseMeaning() + "\n");
                    lastWord = w;
                }
                textView.setText(text.toString());
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w1 = new Word("hello", "你好");
                Word w2 = new Word("word", "世界");
                wordViewModel.insertWords(w1, w2);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w = lastWord;
                // 修改最后一条记录
                if (null != w) {
                    w.setWord("Hi");
                    w.setChineseMeaning("你好啊");
                } else {
                    w = new Word("Hi", "你好啊");
                    w.setId(25);
                }

                wordViewModel.updateWords(w);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w = lastWord;
                // 删除最后一条记录
                if (null == w) {
                    w = new Word();
                    w.setId(26);
                }
                wordViewModel.deleteWords(w);
            }
        });

    }

}