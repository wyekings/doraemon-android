package com.wyekings.composable.compose.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.wyekings.composable.ui.TopBar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScreen() {
    Scaffold(topBar = {
        TopBar(title = "AnimatedVisibility")
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibilitySample()
            CrossFadeSample()

            var show by remember {
                mutableStateOf(false)
            }

            AnimatedContent(targetState = show, label = "animatedContent", transitionSpec = {
                val contentTransform = fadeIn(
                    animationSpec = tween(
                        220, delayMillis = 90
                    )
                ) + scaleIn(
                    initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)
                ) togetherWith fadeOut(animationSpec = tween(90)) using SizeTransform(clip = false)

                contentTransform.apply {
                    targetContentZIndex = if (targetState) -1f else 0f
                }
            }) { state ->
                if (state) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(Color.DarkGray)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Red)
                    )
                }
            }
            Button(onClick = {
                show = !show
            }) {
                Text(text = "CrossFade")
            }
        }
    }
}

@Composable
private fun CrossFadeSample() {
    var change by remember {
        mutableStateOf(false)
    }

    Crossfade(targetState = change, label = "crossFace") {
        if (it) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.DarkGray)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Red)
            )
        }
    }

    Button(onClick = {
        change = !change
    }) {
        Text(text = "CrossFade")
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun ColumnScope.AnimatedVisibilitySample() {
    var show by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = show,
        enter = fadeIn() + slideIn { size ->
            IntOffset(
                x = -size.width, y = -size.height
            )
        } + expandVertically() + scaleIn(),
        exit = fadeOut() + slideOut { size ->
            IntOffset(
                x = size.width, y = size.height
            )
        } + shrinkVertically() + scaleOut(),
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.DarkGray),
        )
    }

//    val a = remember {
//        Animatable(1.dp, Dp.VectorConverter)
//    }

    val transition = updateTransition(targetState = show, label = "visibility")
    transition.AnimatedVisibility(visible = { visible -> visible },
        enter = fadeIn() + slideIn { size ->
            IntOffset(
                x = -size.width, y = -size.height
            )
        } + expandVertically() + scaleIn(),
        exit = fadeOut() + slideOut { size ->
            IntOffset(
                x = size.width, y = size.height
            )
        } + shrinkVertically() + scaleOut()) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(64.dp)
                .background(Color.DarkGray),
        )
    }

    Button(onClick = {
        show = !show
    }) {
        Text(text = "AnimatedVisibility")
    }
}

@Preview
@Composable
private fun AnimatedVisibilityScreenPreview() {
    AnimatedVisibilityScreen()
}