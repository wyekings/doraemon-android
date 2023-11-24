package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DashPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.LightingColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.graphics.SumPathEffect
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw
import com.wyekings.uikit.extensions.saveLayer

class PaintPathEffectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val radius = 50.dp
    private val x = 100.dp
    private val y = 100.dp

    private val path = Path().apply {
        moveTo(10.dp, 40.dp)
        rLineTo(40.dp, 40.dp)
        rLineTo(50.dp, (-70).dp)
        rLineTo(30.dp, 80.dp)
        rLineTo(50.dp, (-60).dp)
        rLineTo(40.dp, 30.dp)
    }

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1.dp
    }
    private val dashPathEffect = DashPathEffect(floatArrayOf(10.dp, 5.dp), 10.dp)
    private val cornerPathEffect = CornerPathEffect(10.dp)
    private val discretePathEffect = DiscretePathEffect(20f, 5f)
    private val sumPathEffect = SumPathEffect(cornerPathEffect, discretePathEffect)
    private val composePathEffect = ComposePathEffect(cornerPathEffect, discretePathEffect)

    private val dashPath = Path().apply {
        lineTo(4.dp, (-6).dp)
        lineTo(8.dp, 0.dp)
        close()
    }
    private val pathDashPathEffect =
        PathDashPathEffect(dashPath, 40f, 0f, PathDashPathEffect.Style.TRANSLATE)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.draw {
//            paint.pathEffect = dashPathEffect
//            drawCircle(x, y, radius, paint)
            drawPath(path, paint)
        }

        canvas.draw {
            translate(0f, 60.dp)
            paint.pathEffect = dashPathEffect
            drawPath(path, paint)
        }

        canvas.draw {
            translate(0f, 120.dp)
            paint.pathEffect = cornerPathEffect
            drawPath(path, paint)
        }

        canvas.draw {
            translate(0f, 180.dp)
            paint.pathEffect = discretePathEffect
            drawPath(path, paint)
        }

        canvas.draw {
            translate(0f, 240.dp)
            paint.pathEffect = pathDashPathEffect
            drawPath(path, paint)
        }

        canvas.draw {
            translate(0f, 300.dp)
            paint.pathEffect = sumPathEffect
            drawPath(path, paint)
        }

        canvas.draw {
            translate(0f, 360.dp)
            paint.pathEffect = composePathEffect
            drawPath(path, paint)
        }
    }
}