package com.example.findme

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import kotlin.random.Random

class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val faceBitmap =
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_face_24)?.toBitmap(300, 300)
    private var faceX = 0f
    private var faceY = 0f

    private val path = Path()
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.black)
    }

    private fun randomPosition() {
        faceX = Random.nextInt(width - 300).toFloat()
        faceY = Random.nextInt(height - 300).toFloat()


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            faceBitmap?.let { drawBitmap(it, faceX, faceY, null) }
            drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    randomPosition()

                    path.reset()
                    path.addRect(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CW)
                    // CCW 相减方向，相当于挖掉
                    path.addCircle(x, y, 300f, Path.Direction.CCW)
                }
                MotionEvent.ACTION_MOVE -> {
                    path.reset()
                    path.addRect(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CW)
                    // CCW 相减方向，相当于挖掉
                    path.addCircle(x, y, 300f, Path.Direction.CCW)
                }
                MotionEvent.ACTION_UP -> {
                    path.reset()

                }
            }
            // 处理完所有的，刷新视图
            invalidate()
        }
        performClick()
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }


}