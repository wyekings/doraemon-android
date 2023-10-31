package com.wyekings.composable.compose.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wyekings.composable.ui.TopBar

@Composable
fun AnimationsScreen() {
    Scaffold(
        topBar = {
            TopBar(title = "Animations")
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            var big by remember { mutableStateOf(false) }
            val target = if (big) 96.dp else 48.dp

            val sizeByTween by animateDpAsState(
                targetValue = target, label = "size", animationSpec = tween(
                    durationMillis = 500, delayMillis = 300, easing = FastOutSlowInEasing
                )
            )
            val sizeBySnap by animateDpAsState(
                targetValue = target, label = "sizeBySnap", animationSpec = snap()
            )

            val sizeByKeyframes by animateDpAsState(
                targetValue = target,
                label = "sizeByKeyframes",
                animationSpec = keyframes {
                    20.dp at 0 with FastOutSlowInEasing
                    40.dp at 100 with LinearOutSlowInEasing
                })

            val sizBySpring by animateDpAsState(
                targetValue = target, label = "sizeBySpring", animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium,
                    visibilityThreshold = 0.5.dp,
                )
            )

            val boomAnim = remember {
                Animatable(initialValue = 48.dp, label = "Boom", typeConverter = Dp.VectorConverter)
            }

            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .size(sizeByTween),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "animate**AsState", fontSize = 9.sp)
            }

            val animatable = remember {
                Animatable(target, Dp.VectorConverter)
            }

            LaunchedEffect(key1 = big) {
                animatable.snapTo(if (big) 192.dp else 12.dp)
                animatable.animateTo(target, animationSpec = keyframes {
                    20.dp at 0 with FastOutSlowInEasing
                    40.dp at 100 with LinearOutSlowInEasing
                })
            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.Green)
                    .size(animatable.value),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Animatable")
            }

            LaunchedEffect(key1 = big) {
                boomAnim.animateTo(
                    48.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium,
                    ),
                    initialVelocity = 1000.dp,
                )
            }

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.Green)
                    .size(boomAnim.value),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Boom")
            }

            Button(onClick = { big = !big }) {
                Text(text = "Toggle")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimationsScreenPreview() {
    AnimationsScreen()
}