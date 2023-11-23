package com.wyekings.components.ui.customview

import androidx.lifecycle.ViewModel
import com.wyekings.components.ui.customview.view.DrawArcView
import com.wyekings.components.ui.customview.view.DrawCircleView
import com.wyekings.components.ui.customview.view.DrawColorView
import com.wyekings.components.ui.customview.view.DrawLinesView
import com.wyekings.components.ui.customview.view.DrawOvalView
import com.wyekings.components.ui.customview.view.DrawPathView
import com.wyekings.components.ui.customview.view.DrawPieChartView
import com.wyekings.components.ui.customview.view.DrawPointsView
import com.wyekings.components.ui.customview.view.DrawRectView
import com.wyekings.components.ui.customview.view.DrawRoundRectView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomViewViewModel @Inject constructor() : ViewModel() {
    val tabModels = arrayListOf(
        TabModel("drawColor", DrawColorView::class.java),
        TabModel("drawCircle", DrawCircleView::class.java),
        TabModel("drawPoints", DrawPointsView::class.java),
        TabModel("drawOval", DrawOvalView::class.java),
        TabModel("drawLines", DrawLinesView::class.java),
        TabModel("drawRoundRect", DrawRoundRectView::class.java),
        TabModel("drawArc", DrawArcView::class.java),
        TabModel("drawPath", DrawPathView::class.java),
        TabModel("drawRect", DrawRectView::class.java),
        TabModel("drawPieChart", DrawPieChartView::class.java),
    ).apply {
        reverse()
    }
}