package com.example.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val colors = arrayOf(
        Color.RED, Color.GREEN, Color.YELLOW,
        Color.MAGENTA, Color.BLUE, Color.GRAY
    )

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private var centerX = 0f
    private var centerY = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        repeat(2000) {

            paint.color = colors.random()
            canvas?.drawCircle(centerX, centerY, it.toFloat() / 5, paint)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        centerX = event?.x ?: 0f
        centerY = event?.y ?: 0f

        invalidate()
        return super.onTouchEvent(event)
    }
}