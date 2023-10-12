package com.wyekings.uikit.insets

import android.app.Activity
import android.graphics.Color
import androidx.core.view.WindowCompat

fun Activity.applySystemBarAppearance(light:Boolean = true) {
    WindowCompat.getInsetsController(window,window.decorView).apply {
        isAppearanceLightStatusBars = light
        isAppearanceLightNavigationBars = light
    }
}

fun Activity.transparentSystemBar() {
    window.apply {
        statusBarColor = Color.TRANSPARENT
        navigationBarColor = Color.TRANSPARENT
    }
}