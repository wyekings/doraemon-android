package com.wyekings.uikit.insetter

import android.view.View

data class InitialPadding(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
)

val View.initialPadding: InitialPadding
    get() = InitialPadding(
        left = this.left,
        top = this.top,
        right = this.right,
        bottom = this.bottom
    )