package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawOvalView @JvmOverloads constructor(
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

        canvas.drawOval(200f, 200f, 500f, 400f, paint)

        paint.style = Paint.Style.STROKE
        canvas.drawOval(600f, 200f, 800f, 600f, paint)
    }
}