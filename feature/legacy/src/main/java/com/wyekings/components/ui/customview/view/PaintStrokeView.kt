package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw

class PaintStrokeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        strokeWidth = 10.dp
    }

    private val path = Path().apply {
        moveTo(10.dp, 140.dp)
        rLineTo(30.dp, 0.dp)
        rLineTo((-15).dp, 20.dp)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.draw {
            paint.strokeCap = Paint.Cap.BUTT
            drawLine(10.dp, 50.dp, 100.dp, 50.dp, paint)
        }
        canvas.draw {
            paint.strokeCap = Paint.Cap.ROUND
            drawLine(10.dp, 70.dp, 100.dp, 70.dp, paint)
        }
        canvas.draw {
            paint.strokeCap = Paint.Cap.SQUARE
            drawLine(10.dp, 90.dp, 100.dp, 90.dp, paint)
        }

        canvas.draw {
            paint.style = Paint.Style.STROKE

            paint.strokeJoin = Paint.Join.BEVEL
            drawPath(path, paint)


            translate(50.dp, 0f)
            paint.strokeJoin = Paint.Join.ROUND
            drawPath(path, paint)

            translate(50.dp, 0f)
            paint.strokeJoin = Paint.Join.MITER
            drawPath(path, paint)

            translate(0f, 100f)
            paint.strokeMiter = 1f
            drawPath(path, paint)

            translate(0f, 100f)
            paint.strokeMiter = 2f
            drawPath(path, paint)

            translate(0f, 100f)
            paint.strokeMiter = 5f
            drawPath(path, paint)
        }
    }
}