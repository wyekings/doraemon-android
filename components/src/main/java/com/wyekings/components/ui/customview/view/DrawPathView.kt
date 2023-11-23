package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class DrawPathView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        setColor(Color.BLACK)
        style = Paint.Style.FILL
    }
    private val path = Path()

    private val linePaint = Paint().apply {
        setColor(Color.BLACK)
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.apply {
            addArc(200f, 200f, 400f, 400f, -225f, 225f)
            arcTo(400f, 200f, 600f, 400f, -180f, 225f, false)
            lineTo(400f, 542f)
        }
        canvas.drawPath(path, paint)

        path.apply {
            addCircle(200f, 800f, 100f, Path.Direction.CW)
            addCircle(300f, 800f, 100f, Path.Direction.CW)
            fillType = Path.FillType.WINDING
        }
        canvas.drawPath(path, paint)

        path.apply {
            addCircle(600f, 800f, 100f, Path.Direction.CW)
            addCircle(700f, 800f, 100f, Path.Direction.CCW)
            fillType = Path.FillType.WINDING
        }
        canvas.drawPath(path, paint)

        path.apply {
            addCircle(200f, 1100f, 100f, Path.Direction.CW)
            addCircle(300f, 1100f, 100f, Path.Direction.CW)
            fillType = Path.FillType.EVEN_ODD
        }
        canvas.drawPath(path, paint)

        paint.style = Paint.Style.FILL
        path.apply {
            moveTo(700f, 300f)
            lineTo(900f, 300f)
            lineTo(800f, 400f)
            close()
        }
        canvas.drawPath(path, paint)

        paint.style = Paint.Style.STROKE
        path.apply {
            moveTo(700f, 200f)
            lineTo(900f, 200f)
            lineTo(1000f, 300f)
            close()
        }

        canvas.drawPath(path, paint)

        paint.textSize = 38f
        canvas.drawText("Hello World", 200f, 1300f, paint)
    }
}