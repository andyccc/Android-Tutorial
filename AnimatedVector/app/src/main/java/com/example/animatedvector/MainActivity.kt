package com.example.animatedvector

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView1.setOnClickListener {
            (imageView1.drawable as AnimatedVectorDrawable).start()
        }

        imageView2.setOnClickListener {
            imageView2.playAnimation()
        }

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
//                TODO("Not yet implemented")
                imageView2.progress = p3
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
//                TODO("Not yet implemented")
            }


        })

    }
}