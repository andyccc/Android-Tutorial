package com.example.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mytag", "onCreate: Activity")
        setContentView(R.layout.activity_main)
        // https://developer.android.com/guide/components/services?hl=zh-cn


    }

    fun startNumberService() {
        Intent(this, MyService::class.java).also {
//            it.putExtra()... 传递参数
            startService(it)
        }
    }

    fun bindNumberService() {
        val bindIntent = Intent(this, MyService::class.java)
        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                // TODO("Not yet implemented")

                (service as MyService.MyBinder).service.numberLiveData.observe(
                    this@MainActivity,
                    Observer {
                        textView.text = "$it"
                    })

            }

            override fun onServiceDisconnected(name: ComponentName?) {
                // TODO("Not yet implemented")
            }
        }
        startService(bindIntent)
        bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStart() {
        super.onStart()
        Log.d("mytag", "onStart: Activity")
//        startNumberService()

        startButton.setOnClickListener {
            startNumberService()
        }

        bindButton.setOnClickListener {
            bindNumberService()
        }

        activityButton.setOnClickListener {
            Intent(this, SecondActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    fun stopNumberService() {

        Intent(this, MyService::class.java).also {
            stopService(it)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("mytag", "onStop: Activity")

//        stopNumberService()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mytag", "onDestroy: Activity")
    }
}