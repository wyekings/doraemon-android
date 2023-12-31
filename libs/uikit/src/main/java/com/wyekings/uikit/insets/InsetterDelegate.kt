package com.wyekings.uikit.insets

import android.app.Activity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.wyekings.uikit.insets.insetter.Insetter

class InsetterDelegate(private val activity: Activity) {
    fun apply(light: Boolean = true, block: (insets: WindowInsetsCompat) -> Unit) {
        activity.transparentSystemBar()
        activity.applySystemBarAppearance(light)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        Insetter.builder().setOnApplyInsetsListener { _, insets, _ ->
                activity.window.decorView.post { block.invoke(insets) }
            }.consume(Insetter.CONSUME_NONE).applyToView(activity.window.decorView)
    }
}