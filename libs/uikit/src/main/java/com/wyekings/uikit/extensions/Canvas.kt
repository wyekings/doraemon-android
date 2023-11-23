package com.wyekings.uikit.extensions

import android.graphics.Canvas

inline fun Canvas.draw(block: Canvas.() -> Unit) {
    save()
    block()
    restore()
}