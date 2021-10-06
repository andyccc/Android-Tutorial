package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button insertBtn, updateBtn, clearBtn, deleteBtn;
    RecyclerView recyclerView;
    Switch aSwitch;

    WordViewModel wordViewModel;
    Word lastWord;
    MyAdapter myAdapter1, myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdapter1 = new MyAdapter(false);
        myAdapter2 = new MyAdapter(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);

        insertBtn = findViewById(R.id.buttonInsert);
        updateBtn = findViewById(R.id.buttonUpdate);
        clearBtn = findViewById(R.id.buttonClear);
        deleteBtn = findViewById(R.id.buttonDelete);
        aSwitch = findViewById(R.id.switchBtn);


        wordViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(WordViewModel.class);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter1.setAllWords(words);
                myAdapter1.notifyDataSetChanged();

                myAdapter2.setAllWords(words);
                myAdapter2.notifyDataSetChanged();
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked ) {
                    recyclerView.setAdapter(myAdapter2);
                } else {
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] english = {
                        "hello",
                        "world",
                        "android",
                        "google",
                        "project",
                        "view",
                        "data"
                };
                String[] chinese = {
                        "你好",
                        "世界",
                        "安卓",
                        "谷歌",
                        "项目",
                        "视图",
                        "数据"
                };

                for (int i = 0; i < english.length; i++) {
                    Word w = new Word(english[i], chinese[i]);
                    wordViewModel.insertWords(w);
                }
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