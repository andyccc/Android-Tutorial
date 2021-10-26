package com.example.animatedvector

import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        imageView.setOnClickListener {
//            (imageView.drawable as AnimatedImageDrawable).start()
//        }

        imageView.setOnClickListener {
            imageView.playAnimation()
        }

    }
}