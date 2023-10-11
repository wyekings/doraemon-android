package com.wyekings.uikit.insetter

import android.view.View
import android.view.ViewGroup
import timber.log.Timber

data class InitialMargin(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
) {
    companion object {
        val EMPTY = InitialMargin(0,0,0,0)
    }
}

val View.initialMargin: InitialMargin
    get() {
        Timber.d(this.layoutParams.javaClass.simpleName)
        val lp = this.layoutParams as? ViewGroup.MarginLayoutParams
            ?: return InitialMargin.EMPTY
        return InitialMargin(
            left = lp.leftMargin,
            top = lp.topMargin,
            right = lp.rightMargin,
            bottom = lp.bottomMargin
        )
    }
