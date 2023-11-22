package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawLinesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        setColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(100f, 200f, 400f, 200f, paint)

        val points = floatArrayOf(
            20f,
            20f,

            120f,
            20f,
            70f,
            20f,
            70f,
            120f,
            20f,
            120f,
            120f,
            120f,
            150f,
            20f,
            250f,
            20f,
            150f,
            20f,
            150f,
            120f,
            250f,
            20f,
            250f,
            120f,
            150f,
            120f,
            250f,
            120f
        )
        canvas.drawLines(points, paint)
    }
}