package com.wyekings.uikit.insets

import android.view.View
import com.wyekings.uikit.insets.insetter.ViewDimensions
import dev.chrisbanes.insetter.ViewState

val View.initialState:ViewState
    get() = ViewState(this)

val View.initialPaddings: ViewDimensions
    get() = initialState.paddings

val View.initialMargins: ViewDimensions
    get() = initialState.margins