package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ComposeShader
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.RadialGradient
import android.graphics.Shader
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.graphics.SweepGradientShader
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw

class PaintShaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val radius = 50.dp
    private val x = 100.dp
    private val y = 100.dp


    private val linearGradientShader = LinearGradient(
        x - radius, y - radius, x + radius, y + radius, intArrayOf(
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
        ), null, Shader.TileMode.CLAMP
    )

    private val radialGradientShader = RadialGradient(
        x + radius * 3, y, radius, intArrayOf(
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
        ), null, Shader.TileMode.CLAMP
    )

    private val sweepGradientShader = SweepGradient(
        x, y + radius * 3, intArrayOf(
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
        ), null
    )

    private val bitmapShader = BitmapShader(
        BitmapFactory.decodeResource(
            resources, com.wyekings.common.R.drawable.ic_tree
        ), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
    )

    private val composeShader: Shader
    private val paint = Paint()

    init {
        // for ComposeShader
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        val batmanShader = BitmapShader(
            BitmapFactory.decodeResource(
                resources, com.wyekings.common.R.drawable.ic_batman
            ), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        val batmanLogoShader = BitmapShader(
            BitmapFactory.decodeResource(
                resources, com.wyekings.common.R.drawable.ic_batman_logo
            ), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )

        composeShader = ComposeShader(batmanShader, batmanLogoShader, PorterDuff.Mode.DST_OVER)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.draw {
            paint.shader = linearGradientShader
            drawCircle(x, y, radius, paint)
        }
        canvas.draw {
            paint.shader = radialGradientShader
            drawCircle(x + radius * 3, y, radius, paint)
        }

        canvas.draw {
            paint.shader = sweepGradientShader
            drawCircle(x, y + radius * 3, radius, paint)
        }

        canvas.draw {
            paint.shader = bitmapShader
            drawCircle(x + radius * 3, y + radius * 3, radius, paint)
        }

        // todo not working but why?
        canvas.draw {
            paint.shader = composeShader
            drawCircle(x, y + radius * 6, radius, paint)
        }

    }
}