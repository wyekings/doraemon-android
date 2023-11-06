package com.wyekings.composable.compose.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
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

            var show by remember { mutableStateOf(true) }
            AnimatedVisibility(
                visible = show,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.DarkGray),
                )
            }

            val transition = updateTransition(targetState = show, label = "visibility")
            transition.AnimatedVisibility(visible = { visible ->
                visible
            }, enter = fadeIn() + slideIn { size ->
                IntOffset(x = -size.width, y = -size.height)
            } + expandVertically() + scaleIn(), exit = fadeOut() + slideOut { size ->
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
    }
}

@Preview
@Composable
private fun AnimatedVisibilityScreenPreview() {
    AnimatedVisibilityScreen()
}