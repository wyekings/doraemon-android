package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RepeatAnimationPage() {

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red, targetValue = Color.Yellow, animationSpec = infiniteRepeatable(
            animation = tween(300),
            repeatMode = RepeatMode.Reverse,
        ), label = "color"
    )

    val animatableColor = remember {
        Animatable(Color.Red)
    }
    LaunchedEffect(Unit) {
        animatableColor.animateTo(
            Color.Yellow,
            animationSpec = repeatable(
                iterations = 4,
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse,
            ),
        )
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .size(100.dp)
            .drawBehind {
                drawRect(color)
            })

        Box(modifier = Modifier
            .padding(top = 20.dp)
            .size(100.dp)
            .drawBehind {
                drawRect(animatableColor.value)
            })

        val rotate by infiniteTransition.animateFloat(
            initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Restart
            ), label = "avatar"
        )

        val rainbowColorsBrush = Brush.sweepGradient(
            listOf(
                Color.Yellow,
                Color.Green,
                Color.Blue,
                Color.Cyan
            )
        )

        Image(painter = painterResource(id = com.wyekings.common.R.drawable.ic_tree),
            contentDescription = "tree",
            modifier = Modifier
                .padding(20.dp)
                .drawBehind {
                    rotate(rotate) {
                        drawCircle(rainbowColorsBrush, style = Stroke(10.dp.toPx()))
                    }
                }
                .clip(CircleShape)
                .size(100.dp))
    }
}
