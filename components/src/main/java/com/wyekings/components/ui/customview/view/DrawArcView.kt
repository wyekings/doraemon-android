package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.px

class DrawArcView @JvmOverloads constructor(
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

        paint.style = Paint.Style.FILL
        canvas.drawArc(50.px, 50.px, 150.px, 100.px, 0f, 360f, true, paint)

        paint.style = Paint.Style.STROKE
        canvas.drawArc(50.px, 120.px, 150.px, 170.px, 0f, 360f, true, paint)

        paint.style = Paint.Style.FILL
        canvas.drawArc(50.px, 190.px, 150.px, 240.px, -110f, 100f, true, paint)
        paint.style = Paint.Style.FILL
        canvas.drawArc(50.px, 190.px, 150.px, 240.px, 20f, 140f, false, paint)
        paint.style = Paint.Style.STROKE
        canvas.drawArc(50.px, 190.px, 150.px, 240.px, 180f, 60f, false, paint)
    }
}