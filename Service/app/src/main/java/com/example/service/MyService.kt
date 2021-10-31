package com.example.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : LifecycleService() {

    private var number = 0
    val numberLiveData = MutableLiveData(0)

//    override fun onBind(intent: Intent): IBinder {
//    }

    override fun onCreate() {
        super.onCreate()
        Log.d("mytag", "onCreate: Service")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("mytag", "onStartCommand: Service")
//        stopSelf()

        lifecycleScope.launch {
            while (true) {
                delay(1000)
                Log.d("mytag", "onStartCommand: ${number++}")
            }

        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mytag", "onDestroy: Service")
    }

    inner class MyBinder : Binder() {
        val service = this@MyService
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("mytag", "onBind: Service")

        super.onBind(intent)
        lifecycleScope.launch {
            while (true) {
                delay(1_000)
                numberLiveData.value = numberLiveData.value?.plus(1)
            }
        }


        return MyBinder()
    }
}