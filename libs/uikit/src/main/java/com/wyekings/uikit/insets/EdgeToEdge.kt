package com.wyekings.uikit.insets

import android.view.View
import android.view.View.OnAttachStateChangeListener
import androidx.activity.ComponentActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import dev.chrisbanes.insetter.ViewState

class EdgeToEdge(private val activity: ComponentActivity) {
    fun apply(light: Boolean = true, block: (insets: WindowInsetsCompat) -> Unit) {
        activity.transparentSystemBar()
        activity.applySystemBarAppearance(light)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        var givenInsetsToDecorView = false
        activity.window.decorView.apply {
            doOnApplyInsets { _, insets, _ ->
                val navigationBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                val isGestureNavigation =
                    navigationBarInsets.bottom <= 24 * activity.resources.displayMetrics.density
                if (!isGestureNavigation) {
                    ViewCompat.onApplyWindowInsets(this, insets)
                    givenInsetsToDecorView = true
                } else if (givenInsetsToDecorView) {
                    ViewCompat.onApplyWindowInsets(
                        this,
                        WindowInsetsCompat.Builder().setInsets(
                            WindowInsetsCompat.Type.navigationBars(), Insets.of(
                                navigationBarInsets.left,
                                navigationBarInsets.top,
                                navigationBarInsets.right,
                                0
                            )
                        ).build(),
                    )
                }
                post {
                    block.invoke(insets)
                }
            }
        }
    }
}

private fun View.doOnApplyInsets(block: (View, WindowInsetsCompat, ViewState) -> Unit) {
    val initialViewState = ViewState(this)
    ViewCompat.setOnApplyWindowInsetsListener(
        this
    ) { v, insets ->
        block.invoke(this@doOnApplyInsets, insets, initialViewState)
        insets
    }
    requestApplyInsetsWhenAttached()
}

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}