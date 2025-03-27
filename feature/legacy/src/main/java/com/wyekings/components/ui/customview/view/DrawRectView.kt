package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw
import com.wyekings.uikit.extensions.sp

class DrawRectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        setColor(Color.BLACK)
    }

    private val histograms = listOf(
        Histogram(9, "Mon"),
        Histogram(39, "Tues"),
        Histogram(25, "Wed"),
        Histogram(19, "Thur"),
        Histogram(39, "Fri"),
        Histogram(22, "Sat"),
        Histogram(30, "Sun"),
    )

    private val rectF = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val start = 30.dp
        val x = 350.dp
        val y = 200.dp
        val gap = 10.dp
        val width = 34.dp
        drawBackground(canvas)
        drawCoordinateSystem(y, x, start, canvas)
        drawTitle(canvas, y)
        drawHistograms(gap, start, width, y, canvas)
    }

    private fun drawHistograms(
        gap: Float,
        start: Float,
        width: Float,
        y: Float,
        canvas: Canvas
    ) {
        histograms.forEachIndexed { index, histogram ->
            val left = (index + 1) * gap + start + width * index
            rectF.left = left
            rectF.top = y - histogram.temp.toFloat() * 10
            rectF.right = left + width
            rectF.bottom = y
            paint.color = Color.GREEN
            canvas.draw {
                drawRect(rectF, paint)
            }

            paint.color = Color.BLACK
            paint.textSize = 11.sp
            paint.isFakeBoldText = false
            paint.textAlign = Paint.Align.CENTER
            canvas.draw {
                drawText(histogram.week, left + width / 2, y + 13.dp, paint)
            }
        }
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.draw {
            drawColor(Color.WHITE)
        }
    }

    private fun drawCoordinateSystem(y: Float, x: Float, start: Float, canvas: Canvas) {
        val coordinateSystem = floatArrayOf(
            start, start,
            start, y,
            start, y,
            x, y,
        )
        canvas.draw {
            paint.color = Color.GRAY
            drawLines(coordinateSystem, paint)
        }
    }

    private fun drawTitle(canvas: Canvas, y: Float) {
        canvas.draw {
            paint.color = Color.BLACK
            paint.textSize = 13.sp
            paint.textAlign = Paint.Align.CENTER
            paint.isFakeBoldText = true
            drawText("Weekly temperature", width / 2f, y + 30.dp, paint)
        }
    }
}

data class Histogram(
    val temp: Int, val week: String,
)