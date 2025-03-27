package com.wyekings.components.ui.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Align
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.wyekings.uikit.extensions.dp
import com.wyekings.uikit.extensions.draw
import com.wyekings.uikit.extensions.sp
import kotlin.math.cos
import kotlin.math.sin

class DrawPieChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        setColor(Color.BLACK)
    }
    private val rectF = RectF()

    private val percentages = listOf(
        Percentage("Apple", 40, Color.YELLOW),
        Percentage("Huawei", 20, Color.GREEN),
        Percentage("Xiaomi", 25, Color.BLUE),
        Percentage("Samsung", 10, Color.CYAN),
        Percentage("Other", 5, Color.RED),
    )

    private val total = percentages.sumOf { it.percent }
    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawTitle(canvas)

        var startAngle = 0f
        var sweepAngle = 0f

        val radius = 80.dp
        val offset = 10.dp
        val centerX = width / 2f
        val centerY = height / 2f

        percentages.forEachIndexed { index, percentage ->
            startAngle += sweepAngle
            sweepAngle = percentage.percent.toFloat() / total * 360
            val selected = index == 0
            val midAngle = startAngle + sweepAngle / 2
            val x: Float
            val y: Float
            if (selected) {
                x = centerX + offset * cos(midAngle * Math.PI / 180).toFloat()
                y = centerY + offset * sin(midAngle * Math.PI / 180).toFloat()
            } else {
                x = centerX
                y = centerY
            }
            rectF.apply {
                left = x - radius
                top = y - radius
                right = x + radius
                bottom = y + radius
            }
            canvas.draw {
                paint.style = Paint.Style.FILL
                paint.color = percentage.color
                drawArc(rectF, startAngle, sweepAngle, true, paint)
            }

            val lineX: Float = x + radius * cos(midAngle * Math.PI / 180).toFloat()
            val lingY: Float = y + radius * sin(midAngle * Math.PI / 180).toFloat()
            val turningX: Float = x + (radius + 20.dp) * cos(midAngle * Math.PI / 180).toFloat()
            val turningY: Float = y + (radius + 20.dp) * sin(midAngle * Math.PI / 180).toFloat()
            val offsetX = if (lineX > x) 40.dp else (-40).dp
            canvas.draw {
                paint.style = Paint.Style.STROKE
                paint.color = Color.LTGRAY
                paint.strokeWidth = 1.dp
                path.moveTo(lineX, lingY)
                path.lineTo(turningX, turningY)
                path.lineTo(turningX + offsetX, turningY)
                drawPath(path, paint)
            }

            val textX = turningX + offsetX / 2
            canvas.draw {
                paint.textAlign = Align.CENTER
                paint.textSize = 10.sp
                paint.color = Color.BLACK
                paint.isFakeBoldText = false
                drawText(percentage.name, textX, turningY - 10.dp, paint)
            }
        }
    }

    private fun drawTitle(canvas: Canvas) {
        paint.textAlign = Align.CENTER
        paint.isFakeBoldText = true
        paint.textSize = 15.sp
        paint.color = Color.BLACK
        canvas.draw {
            drawText("Mobile phone proportion distribution", width / 2f, 50.dp, paint)
        }
    }
}

private class Percentage(
    val name: String,
    val percent: Int,
    @ColorInt val color: Int,
)