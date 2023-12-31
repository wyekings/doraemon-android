package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp

class DrawCircleView @JvmOverloads constructor(
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
        canvas.drawCircle(200f, 200f, 100f, paint)

        paint.setColor(Color.RED)
        canvas.drawCircle(600f, 200f, 100f, paint)

        paint.setColor(Color.BLACK)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f
        canvas.drawCircle(200f, 600f, 100f, paint)

        paint.setColor(Color.BLACK)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20.dp
        canvas.drawCircle(600f, 600f, 100f, paint)
    }
}