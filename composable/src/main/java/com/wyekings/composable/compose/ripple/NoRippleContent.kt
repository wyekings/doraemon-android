package com.wyekings.composable.compose.ripple

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun NoRippleContent(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        content()
    }
}