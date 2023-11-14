package com.wyekings.uikit.extensions

import android.content.res.Resources
import android.util.TypedValue


/**
 * dp to px
 */
val Number.px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics
    )