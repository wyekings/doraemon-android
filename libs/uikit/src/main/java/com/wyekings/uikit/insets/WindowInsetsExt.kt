package com.wyekings.uikit.insets

import androidx.core.view.WindowInsetsCompat

val WindowInsetsCompat.statusBarHeight: Int
    get() = getInsets(WindowInsetsCompat.Type.statusBars()).top

val WindowInsetsCompat.navigationBarHeight: Int
    get() = getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

val WindowInsetsCompat.imeHeight: Int
    get() = getInsets(WindowInsetsCompat.Type.ime()).bottom

val WindowInsetsCompat.bottomHeight: Int
    get() = getInsets(WindowInsetsCompat.Type.ime() or WindowInsetsCompat.Type.navigationBars()).bottom
