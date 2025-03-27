package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.sp

class FillTextPathView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val path = Path().apply {
        moveTo(50f, 100f);
        rLineTo(50f, 100f);
        rLineTo(80f, -150f);
        rLineTo(100f, 100f);
        rLineTo(70f, -120f);
        rLineTo(150f, 80f);
    }
    private val fillPath1 = Path()
    private val fillPath2 = Path()
    private val fillPath3 = Path()
    private val textPath = Path()

    private val paint = Paint().apply {
        setColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 0f
        paint.getFillPath(path, fillPath1)
        canvas.drawPath(path, paint)

        canvas.save()
        canvas.translate(180.dp, 0f)
        canvas.drawPath(fillPath1, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(0f, 40.dp)
        paint.style = Paint.Style.STROKE
        paint.getFillPath(path, fillPath2)
        canvas.drawPath(path, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(180.dp, 80f)
        canvas.drawPath(fillPath2, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(0f, 120.dp)
        paint.strokeWidth = 10.dp
        paint.style = Paint.Style.STROKE
        paint.getFillPath(path, fillPath3)
        canvas.drawPath(path, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(180.dp, 120.dp)
        paint.strokeWidth = 0f
        canvas.drawPath(fillPath3, paint)
        canvas.restore()

        val text = "Hello World"
        paint.textSize = 16.sp
        paint.style = Paint.Style.FILL
        paint.getTextPath(text, 0, text.length, 150.dp, 280.dp, textPath)
        canvas.drawText(text, 150.dp, 250.dp, paint)

        paint.style = Paint.Style.STROKE
        canvas.drawPath(textPath, paint)
    }
}