package com.wyekings.components.ui.customview

import android.view.View

data class TabModel(
    val title: String,
    val viewClass:Class<out View>
)