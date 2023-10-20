package com.wyekings.uikit.insets

import android.app.Activity
import android.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

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

fun Activity.immersive(immersive:Boolean = true) {
    WindowCompat.getInsetsController(window,window.decorView).apply {
        if (immersive) {
            hide(WindowInsetsCompat.Type.systemBars())
        } else {
            show(WindowInsetsCompat.Type.systemBars())
        }
    }
}