package com.wyekings.composeable.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Square(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
        content()
    }
}