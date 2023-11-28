package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateAppearingDisappearingPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var visible by remember {
            mutableStateOf(true)
        }

        val animatedAlpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            label = "alpha",
        )

        AnimatedVisibility(
            visible = visible,
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .animateEnterExit(
                        enter = slideInHorizontally(),
                        exit = slideOutHorizontally(),
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Red),
            )
        }

        Box(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
                .graphicsLayer {
                    alpha = animatedAlpha
                }
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Yellow),
        )

        AnimatedVisibility(
            visible = visible,
        ) {
            val color by transition.animateColor(label = "background") { state ->
                if (state == EnterExitState.Visible) Color.Green else Color.LightGray
            }

            Box(modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .drawBehind {
                    drawRect(color = color)
                }

            )
        }

        Button(
            onClick = {
                visible = !visible
            },
        ) {
            Text(text = "Show/Hide")
        }
    }
}

@Preview
@Composable
private fun AnimatedVisibilityPagePreview() {
    AnimateAppearingDisappearingPage()
}