

package com.example.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bindButton
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bindButton.setOnClickListener {
            bindNumberService()
        }

    }

    fun bindNumberService() {
        val bindIntent = Intent(this, MyService::class.java)
        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                // TODO("Not yet implemented")

                (service as MyService.MyBinder).service.numberLiveData.observe(
                    this@SecondActivity,
                    Observer {
                        textView2.text = "$it"
                    })

            }

            override fun onServiceDisconnected(name: ComponentName?) {
                // TODO("Not yet implemented")
            }
        }
        bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }
}