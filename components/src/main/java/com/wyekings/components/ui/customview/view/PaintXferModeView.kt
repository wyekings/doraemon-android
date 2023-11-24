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
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw
import com.wyekings.uikit.extensions.saveLayer

class PaintXferModeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val radius = 50.dp
    private val x = 100.dp
    private val y = 100.dp


    private val paint = Paint()
    private val batmanBitmap =
        BitmapFactory.decodeResource(resources, com.wyekings.common.R.drawable.ic_batman)
    private val batmanLogoBitmap =
        BitmapFactory.decodeResource(resources, com.wyekings.common.R.drawable.ic_batman_logo)

    private val xferMode1 = PorterDuffXfermode(PorterDuff.Mode.SRC)
    private val xferMode2 = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private val xferMode3 = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.saveLayer {
            draw {
                drawBitmap(batmanBitmap, 0f, 0f, paint)
            }

            draw {
                paint.xfermode = xferMode1
                drawBitmap(batmanLogoBitmap, 0f, 0f, paint)
                paint.xfermode = null
            }

            draw {
                drawBitmap(batmanBitmap, 0f, batmanBitmap.height + 10.dp, paint)
            }

            draw {
                paint.xfermode = xferMode2
                drawBitmap(batmanLogoBitmap, 0f, batmanBitmap.height + 10.dp, paint)
                paint.xfermode = null
            }

            draw {
                drawBitmap(batmanBitmap, 0f, batmanBitmap.height * 2 + 10.dp, paint)
            }

            draw {
                paint.xfermode = xferMode3
                drawBitmap(batmanLogoBitmap, 0f, batmanBitmap.height * 2 + 10.dp, paint)
                paint.xfermode = null
            }
        }
    }
}