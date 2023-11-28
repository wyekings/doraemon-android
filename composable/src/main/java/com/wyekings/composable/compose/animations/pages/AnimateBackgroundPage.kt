package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimateBackgroundPage() {
    var animate by remember {
        mutableStateOf(false)
    }
    val animatedColor by animateColorAsState(
        targetValue = if (animate) Color.Red else Color.Yellow, label = "backgroundColor"
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .drawBehind {
                drawRect(animatedColor)
            }
            .clickable {
                animate = !animate
            },
    )
}

@Preview
@Composable
private fun AnimateBackgroundPagePreview() {
    AnimateBackgroundPage()
}