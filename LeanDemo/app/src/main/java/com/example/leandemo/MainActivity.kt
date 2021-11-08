package com.example.leandemo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.leancloud.LCUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MyViewModel::class.java)
        val myAdapter = MyAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = myAdapter
        }

        myViewModel.dataListLive.observe(this, Observer {
            myAdapter.updateDataList(it)
            myAdapter.notifyDataSetChanged()
        })

        floatingActionButton.setOnClickListener {
            val v = LayoutInflater.from(this).inflate(R.layout.new_word_dialog, null)
            AlertDialog.Builder(this)
                .setTitle("添加")
                .setView(v)
                .setPositiveButton("确定") { dialog, which ->
                    val newWord = v.findViewById<TextView>(R.id.editTextNewWord).text.toString()
                    myViewModel.addWord(newWord)
                }
                .setNegativeButton("取消") {dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.loginOut) {
            LCUser.logOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}