package com.wyekings.uikit.insets.insetter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.applyStatusBarInsets(applyByMargin: Boolean = false) {
    applyInsetter {
        type(statusBars = true) {
            applyType(applyByMargin)
        }
    }
}

fun View.applyNavigationBarInsets(applyByMargin: Boolean = false) {
    if (this is RecyclerView) {
        clipToPadding = false
    }
    applyInsetter {
        type(navigationBars = true) {
            applyType(applyByMargin)
        }
    }
}

fun View.applyImeInsets(vararg views: View, applyByMargin: Boolean = false) {
    applyInsetter {
        type(navigationBars = true, ime = true) {
            applyType(applyByMargin, animated = true)
        }
        syncTranslationTo(*views)
    }
}

private fun InsetterApplyTypeDsl.applyType(
    applyByMargin: Boolean = false, animated: Boolean = false
) {
    if (applyByMargin) {
        margin(animated = animated)
    } else {
        padding(animated = animated)
    }
}
