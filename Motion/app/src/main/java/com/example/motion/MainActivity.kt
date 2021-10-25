package com.example.motion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 动态修改动画属性值
        val set = motionLayout.getConstraintSet(R.id.end)
        set.setRotation(R.id.view, 90000f)
        motionLayout.setTransitionDuration(2500)
        motionLayout.updateState(R.id.end, set)
    }
}