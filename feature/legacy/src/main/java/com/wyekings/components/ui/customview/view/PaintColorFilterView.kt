package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.LightingColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw

class PaintColorFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val radius = 50.dp
    private val x = 100.dp
    private val y = 100.dp


    private val paint = Paint()
    private val bitmap =
        BitmapFactory.decodeResource(resources, com.wyekings.common.R.drawable.ic_tree)
    private val filter1 = LightingColorFilter(0x00ffff, 0x000000)
    private val filter2 = LightingColorFilter(0xffffff, 0x003000)
    private val filter3 = ColorMatrixColorFilter(ColorMatrix().apply {
        setSaturation(0f)
    })
    private val filter4 = PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.DST_OVER)

    private val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = bitmapShader

        canvas.draw {
            paint.colorFilter = filter1
            drawCircle(x, y, radius, paint)
        }

        canvas.draw {
            paint.colorFilter = filter2
            drawCircle(x + radius * 3, y, radius, paint)
        }

        canvas.draw {
            paint.colorFilter = filter3
            drawCircle(x, y + radius * 3, radius, paint)
        }
        canvas.draw {
            paint.colorFilter = filter4
            drawCircle(x + radius * 3, y + radius * 3, radius, paint)
        }
    }
}