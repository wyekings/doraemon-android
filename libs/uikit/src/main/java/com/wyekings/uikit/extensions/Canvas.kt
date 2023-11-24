package com.wyekings.uikit.extensions

import android.graphics.Canvas

inline fun Canvas.draw(block: Canvas.() -> Unit) {
    save()
    block()
    restore()
}

inline fun Canvas.saveLayer(block: Canvas.() -> Unit) {
    val saved = saveLayer(null, null)
    block()
    restoreToCount(saved)
}
