package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawPointsView @JvmOverloads constructor(
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

        paint.strokeCap = Paint.Cap.BUTT
        paint.strokeWidth = 20f
        canvas.drawPoint(200f, 200f, paint)

        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 40f
        canvas.drawPoint(400f, 200f, paint)

        paint.strokeCap = Paint.Cap.SQUARE
        paint.strokeWidth = 60f
        canvas.drawPoint(600f, 200f, paint)

        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 40f
        val points = floatArrayOf(
            200f,
            400f,
            400f,
            400f,
            600f,
            400f,

            200f,
            600f,
            400f,
            600f,
            600f,
            600f,

            200f,
            800f,
            400f,
            800f,
            600f,
            800f,
        )
        canvas.drawPoints(points,paint)
//        canvas.drawPoints(points, 2, 6, paint)
    }
}