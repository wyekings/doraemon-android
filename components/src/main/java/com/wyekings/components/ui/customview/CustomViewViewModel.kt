package com.wyekings.components.ui.customview

import androidx.lifecycle.ViewModel
import com.wyekings.components.ui.customview.view.CustomView
import com.wyekings.components.ui.customview.view.DrawColorView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomViewViewModel @Inject constructor() : ViewModel() {
    val tabModels = arrayListOf(
        TabModel("draColor", DrawColorView::class.java),
        TabModel("drawCircle", CustomView::class.java),
    )
}