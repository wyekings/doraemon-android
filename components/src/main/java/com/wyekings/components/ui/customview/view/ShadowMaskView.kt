package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw
import com.wyekings.uikit.extensions.sp

class ShadowMaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val radius = 50.dp
    private val x = 100.dp
    private val y = 100.dp

    private val paint = Paint().apply {
        setColor(Color.BLACK)
    }

    private val bitmap =
        BitmapFactory.decodeResource(resources, com.wyekings.common.R.drawable.ic_tree)
    private val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    private val maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.draw {
            paint.textSize = 14.sp
            paint.textAlign = Paint.Align.CENTER
            paint.setShadowLayer(10f, 0f, 0f, Color.RED)
            drawText("Hello World", width / 2f, 20.dp, paint)

            paint.shader = bitmapShader
            paint.maskFilter = maskFilter
            drawCircle(x, y, radius, paint)
        }
    }
}