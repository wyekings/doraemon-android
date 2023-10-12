package com.wyekings.uikit.insets

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.Px
import androidx.core.view.updateLayoutParams

fun View.updateMargins(
    @Px left:Int = -1,
    @Px top:Int = -1,
    @Px right:Int = -1,
    @Px bottom:Int = -1,
) {

    val isMarginLayoutParams = (this.layoutParams as? MarginLayoutParams) != null
    if (isMarginLayoutParams) {
        updateLayoutParams<MarginLayoutParams> {
            if (left != -1) {
                this.leftMargin = left
            }
            if (top != -1) {
                this.topMargin = top
            }
            if (right != -1) {
                this.rightMargin = right
            }
            if (bottom != -1) {
                this.bottomMargin = bottom
            }
        }
    }
}