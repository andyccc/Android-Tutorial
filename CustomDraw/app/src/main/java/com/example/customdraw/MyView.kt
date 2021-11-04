package com.example.customdraw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), LifecycleObserver {

    private var mWidth = 0f;
    private var mHeight = 0f;
    private var mRadius = 0f

    private var mAngle = 10f

    private var rotatingJob: Job? = null

    private var sineWaveSamplsPath = Path()

    private val fillCirclePaint = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.colorWhite)
    }

    private val solidLinePaint = Paint()
        .apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = ContextCompat.getColor(context, R.color.colorWhite)
        }


    private val vectorLinePaint = Paint()
        .apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = ContextCompat.getColor(context,R.color.teal_700)
        }


    private val textPaint = Paint().apply {
        textSize = 20f
        typeface = Typeface.DEFAULT_BOLD
        color = ContextCompat.getColor(context, R.color.colorWhite)
    }

    private val dashedLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(10f,10f), 0f)
        color = ContextCompat.getColor(context, R.color.colorYellow)
        strokeWidth = 5f
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
            drawVector(this)
            drawProjections(this)
            drawSineWave(this)
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

        mRadius = if (mRadius <  h / 4)  mRadius else h / 4.toFloat()

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
            drawRect(50f,50f,300f,120f,solidLinePaint)
            drawText("指数函数与旋转矢量",60f,100f, textPaint)
        }
    }

    private fun drawDashedCircle(canvas: Canvas) {
        canvas.withTranslation(mWidth/2, mHeight /4*3) {
            drawCircle(0f,0f,mRadius, dashedLinePaint)

        }
    }

    private fun drawVector(canvas: Canvas) {
        canvas.withTranslation(mWidth/2, mHeight /4*3) {
            withRotation(mAngle) {
                drawLine(0f,0f,mRadius,0f, vectorLinePaint)
            }
        }
    }

    private fun drawProjections(canvas: Canvas) {
        canvas.withTranslation(mWidth/2, mHeight / 2) {
            drawCircle(mRadius * cos(mAngle.toRadians()), 0f,10f, fillCirclePaint)
        }

        canvas.withTranslation(mWidth/2, mHeight / 4 *3) {
            drawCircle(mRadius * cos(mAngle.toRadians()), 0f,10f, fillCirclePaint)
        }

        canvas.withTranslation(mWidth/2, mHeight /4 * 3) {
            val x = mRadius * cos(mAngle.toRadians())
            val y = mRadius * sin(mAngle.toRadians())
            withTranslation(x ,-y) {
                drawLine(0f,0f, 0f, y, solidLinePaint)
                drawLine(0f,0f, 0f,-mHeight/4 + y, dashedLinePaint)
            }

        }

    }

    private fun drawSineWave(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight /2) {
            val sampleCount = 50
            val dy = mHeight / 2 / sampleCount

            sineWaveSamplsPath.reset()
            sineWaveSamplsPath.moveTo(mRadius * cos(mAngle.toRadians()), 0f)
            repeat(sampleCount) {
                val x = mRadius * cos(it * -0.15 + mAngle.toRadians())
                val y = -dy * it
                sineWaveSamplsPath.quadTo(x.toFloat(), y ,x.toFloat(), y)
            }
            drawPath(sineWaveSamplsPath, vectorLinePaint)
            drawTextOnPath("hello", sineWaveSamplsPath, 1000f, 0f, textPaint)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startRotating() {
        rotatingJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(100)
                mAngle += 5f
                invalidate()

            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseRotationg(){
        rotatingJob?.cancel()

    }

    fun Float.toRadians() = this / 180 * PI.toFloat()




}