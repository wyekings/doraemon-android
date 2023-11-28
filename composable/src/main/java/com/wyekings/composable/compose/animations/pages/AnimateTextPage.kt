package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun AnimateTextPage() {

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 2f, animationSpec = infiniteRepeatable(
            tween(1000), RepeatMode.Reverse
        ), label = "scale"
    )

    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF60DDAD),
        targetValue = Color(0xFF4285F4),
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Hello Compose",
            modifier = Modifier
                .padding(20.dp)
                .graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                    transformOrigin = TransformOrigin.Center
                },
            color = animatedColor,
            fontSize = 18.sp,
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
    }
}