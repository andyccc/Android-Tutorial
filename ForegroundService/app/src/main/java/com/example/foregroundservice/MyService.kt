package com.example.foregroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val CHANNEL_ID = "MY_CHANNEL_ID"

class MyService : LifecycleService() {

    val numberLiveData = MutableLiveData<Int>(0)

    private var number = 0

    override fun onCreate() {
        super.onCreate()
        lifecycleScope.launch { 
            while (true) {
                delay(1000)
//                Log.d("mytag", "onCreate: ${number++}")
                numberLiveData.value = numberLiveData.value?.plus(1)
            }
            
        }

        createChannel()

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_android_black)
            .setContentTitle("this is a title.")
            .setContentText("this is a noti")
            .setContentIntent(pendingIntent)
            .build()

//        startForeground(1, notification)
    }

    inner class Mybinder: Binder() {
        val getService = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)

        return Mybinder()
    }

    fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}