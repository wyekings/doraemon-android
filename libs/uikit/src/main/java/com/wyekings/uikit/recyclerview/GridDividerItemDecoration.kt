package com.wyekings.uikit.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

/**
 * @author wye
 * @date 2020-09-03
 */
class GridDividerItemDecoration(
  private val spanCount: Int,
  @ColorInt val dividerColor: Int = Color.parseColor("#DEE0E2"),
  dividerWidth: Float = 1f
) : RecyclerView.ItemDecoration() {

  private val dividerPaint = Paint()

  init {
    dividerPaint.strokeWidth = dividerWidth
    dividerPaint.color = dividerColor
    dividerPaint.style = Paint.Style.STROKE
  }

  override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    super.onDrawOver(c, parent, state)
    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)
      val position = parent.getChildLayoutPosition(child)
      val column: Int = (position + 1) % spanCount
      val params = child.layoutParams as RecyclerView.LayoutParams
      val childBottom = child.bottom + params.bottomMargin + child.translationY.roundToInt()
      val childRight = child.right + params.rightMargin + child.translationX.roundToInt()
      if (childBottom < parent.height) {
        c.drawLine(
          child.left.toFloat(),
          childBottom.toFloat(),
          childRight.toFloat(),
          childBottom.toFloat(),
          dividerPaint
        )
      }
      if (column < spanCount) {
        c.drawLine(
          childRight.toFloat(),
          child.top.toFloat(),
          childRight.toFloat(),
          childBottom.toFloat(),
          dividerPaint
        )
      }
    }
  }
}
