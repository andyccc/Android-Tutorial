package com.example.surfaceview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView

class MySurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {

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


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        centerX = event?.x ?: 0f
        centerY = event?.y ?: 0f

        val canvas = holder.lockCanvas()
        canvas.drawColor(Color.BLACK) // 只需要画个颜色就可以清空之前的内容
        repeat(2000) {
            paint.color = colors.random()
            canvas?.drawCircle(centerX, centerY, it.toFloat() / 5, paint)
        }
        holder.unlockCanvasAndPost(canvas)

        return super.onTouchEvent(event)
    }

}