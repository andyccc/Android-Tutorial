package com.example.kotlindemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.SavedStateViewModelFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        test2()
    }

    fun test2() {
        val viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application,this)).get(MyViewModel::class.java)
        viewModel.number.observe(this, Observer {
            textView.text = it.toString()
        })

        buttonPlus.setOnClickListener {
            viewModel.modifyNumber(1)
        }

        buttonMinus.setOnClickListener{
            viewModel.modifyNumber(-1)
        }
    }

    fun test1() {
        var number = 0
        textView.text = "0"
        buttonPlus.setOnClickListener {
            textView.text = (++number).toString()
        }

        buttonMinus.setOnClickListener{
            textView.text = (--number).toString()
        }

    }

}