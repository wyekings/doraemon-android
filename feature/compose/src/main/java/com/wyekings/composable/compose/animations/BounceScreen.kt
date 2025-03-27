package com.wyekings.composable.compose.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wyekings.composable.ui.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BounceScreen() {
    Scaffold(
        topBar = {
            TopBar(title = "Bounce")
        },
    ) {
//        BounceWithUpdateBounds(it)
        Bounce(it)
    }
}

@Composable
private fun Bounce(it: PaddingValues) {
    BoxWithConstraints(modifier = Modifier.padding(it)) {
        val boxSize = 50.dp

        val initialVelocity = 5000.dp
        val anim = remember { Animatable(0.dp, Dp.VectorConverter) }
        val decaySpec = remember { exponentialDecay<Dp>() }

        LaunchedEffect(Unit) {
            delay(1000)
            anim.animateDecay(initialVelocity, decaySpec)
        }

        val paddingX = padding(anim.value, boxSize, maxWidth)
        val paddingY = padding(anim.value, boxSize, maxHeight)

        Box(
            modifier = Modifier
                .padding(start = paddingX, top = paddingY)
                .size(boxSize)
                .background(Color.DarkGray)
        )
    }
}

@Composable
private fun padding(
    animValue: Dp,
    boxSize: Dp,
    max: Dp,
): Dp = remember(animValue) {
    val padding = (animValue.value % ((max - boxSize) * 2).value).dp
    if (padding < max - boxSize) {
        padding
    } else {
        // anim.value - (max - boxSize) = max - boxSize - padding  -> padding = (max - boxSize) * 2 - anim.value
        (max - boxSize) * 2 - padding
    }
}

@Composable
private fun BounceWithUpdateBounds(it: PaddingValues) {
    BoxWithConstraints(modifier = Modifier.padding(it)) {
        val boxSize = 50.dp
        val initialVelocity = 5000.dp
        val animX = remember { Animatable(0.dp, Dp.VectorConverter) }
        val animY = remember { Animatable(0.dp, Dp.VectorConverter) }
        animX.updateBounds(lowerBound = 0.dp, upperBound = maxWidth - boxSize)
        animY.updateBounds(lowerBound = 0.dp, upperBound = maxHeight - boxSize)
        val decaySpec = remember { exponentialDecay<Dp>() }
        LaunchedEffect(key1 = Unit) {
            delay(1000)
            launch {
                var result = animX.animateDecay(initialVelocity, decaySpec)
                while (result.endReason == AnimationEndReason.BoundReached) {
                    result = animX.animateDecay(-result.endState.velocity, decaySpec)
                }
            }
            launch {
                var result = animY.animateDecay(initialVelocity, decaySpec)
                while (result.endReason == AnimationEndReason.BoundReached) {
                    result = animY.animateDecay(-result.endState.velocity, decaySpec)
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(start = animX.value, top = animY.value)
                .size(boxSize)
                .background(Color.DarkGray)
        )
    }
}

@Preview
@Composable
private fun BounceScreenPreview() {
    BounceScreen()
}