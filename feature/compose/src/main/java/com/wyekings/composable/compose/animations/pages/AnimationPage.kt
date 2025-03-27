package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AnimationPage() {
    val targetBasedAnimation = remember {
        TargetBasedAnimation(
            animationSpec = tween(1000),
            typeConverter = Float.VectorConverter,
            initialValue = 0f,
            targetValue = 200f
        )
    }

    var playTime by remember {
        mutableLongStateOf(0L)
    }
    LaunchedEffect(targetBasedAnimation) {
        val startTime = withFrameNanos { it }

        do {
            playTime = withFrameNanos { it } - startTime
            val animateValue = targetBasedAnimation.getValueFromNanos(playTimeNanos = playTime)
        } while (true)
    }

    val decaySpec = remember {
        FloatExponentialDecaySpec()
    }
    val decayAnimation = remember {
        DecayAnimation(
            animationSpec = decaySpec, initialValue = 0f
        )
    }
}