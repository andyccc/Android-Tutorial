package com.example.customdraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withTranslation

class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0f;
    private var mHeight = 0f;
    private var mRadius = 0f

    private val solidLinePaint = Paint()
        .apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = ContextCompat.getColor(context, R.color.colorWhite)
        }

    private val textPaint = Paint().apply {
        textSize = 50f
        typeface = Typeface.DEFAULT_BOLD
        color = ContextCompat.getColor(context, R.color.colorWhite)
        strokeWidth = 5f
    }

    private val dashedLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(10f,10f), 0f)
        color = ContextCompat.getColor(context, R.color.colorYellow)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        Log.d("mytag", "onFinishInflate: ")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("mytag", "onAttachedToWindow: ")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("mytag", "onMeasure: ")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("mytag", "onLayout: ")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("mytag", "onDraw: ")

        canvas?.apply {
            drawAxises(this)
            drawLabel(this)
            drawDashedCircle(this)
        }


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("mytag", "onDetachedFromWindow: ")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("mytag", "onSizeChanged: ")
        mWidth = w.toFloat()
        mHeight = h.toFloat()

        mRadius = if (w < h) w/2.toFloat() else h / 4.toFloat()
        mRadius -= 20f

    }

    private fun drawAxises(canvas: Canvas) {
//        canvas.drawLine(100f,100f,100f,400f,solidLinePaint)

//        canvas.save()
//        canvas.rotate()
//        canvas.drawLine()
//        canvas.restore()

        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint)
            drawLine(0f, -mHeight / 2, 0f, mHeight / 2, solidLinePaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight / 4 * 3) {
            drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint)


        }

    }


    private fun drawLabel(canvas: Canvas) {
        canvas.apply {
            drawRect(100f,100f,600f,250f,solidLinePaint)
            drawText("指数函数与旋转矢量",120f,195f, textPaint)
        }
    }

    private fun drawDashedCircle(canvas: Canvas) {
        canvas.withTranslation(mWidth/2, mHeight /4*3) {
            drawCircle(0f,0f,mRadius, dashedLinePaint)

        }

    }

}