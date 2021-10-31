package com.example.foregroundservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceConnection = object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                (service as MyService.Mybinder).getService.numberLiveData.observe(this@MainActivity, Observer {
                    textView.text = "${it}"
                })
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }


        Intent(this, MyService::class.java).apply {
            startService(this)
            bindService(this, serviceConnection, BIND_AUTO_CREATE)
        }

    }
}