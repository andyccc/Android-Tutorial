package com.example.surfaceview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BubbleSurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {

    private val colors = arrayOf(
        Color.RED, Color.GREEN, Color.YELLOW,
        Color.MAGENTA, Color.BLUE, Color.GRAY
    )

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        pathEffect = ComposePathEffect(CornerPathEffect(50f), DiscretePathEffect(30f, 20f))
//        pathEffect = DiscretePathEffect(30f, 20f)
    }

    private data class Bubble(val x: Float, val y: Float, val color: Int, var radius: Float)

    private val bubblesList = mutableListOf<Bubble>()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x?: 0f
        val y = event?.y?: 0f
        val color = colors.random()

        val bubble = Bubble(x, y , color, 1f)
        bubblesList.add(bubble)

        if (bubblesList.size > 30) {
            bubblesList.removeAt(0)
        }


        return super.onTouchEvent(event)
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (holder.surface.isValid) {
                    val canvas = holder.lockCanvas()
                    canvas.drawColor(Color.BLACK)
                    bubblesList.toList().filter {
                        it.radius < 3000
                    }.forEach {
                        paint.color = it.color
                        canvas.drawCircle(it.x, it.y, it.radius, paint)
                        it.radius += 10f
                    }

                    holder.unlockCanvasAndPost(canvas)
                }

            }
        }
    }


}