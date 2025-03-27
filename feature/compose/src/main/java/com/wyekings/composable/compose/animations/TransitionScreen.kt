package com.wyekings.composable.compose.animations

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wyekings.composable.ui.TopBar

@Composable
fun TransitionScreen() {
    Scaffold(
        topBar = {
            TopBar(title = "Transition")
        },
    ) {

        var expanded by remember { mutableStateOf(false) }
        val transitionState = remember { MutableTransitionState(!expanded) }
        transitionState.targetState = expanded
        val transition = rememberTransition(transitionState = transitionState, label = "transition")
//        val transition = updateTransition(label = "transition", targetState = expanded)
//        val infiniteTransition = rememberInfiniteTransition(label = "infinite")
        val size by transition.animateDp(label = "size", transitionSpec = {
            when {
                false isTransitioningTo true -> spring()
                else -> tween()
            }
        }) { state ->
            if (state) 128.dp else 64.dp
        }
        val corner by transition.animateDp(label = "label") { state ->
            if (state) 12.dp else 0.dp
        }

        Box(
            modifier = Modifier
                .padding(it)
                .size(size)
                .clip(RoundedCornerShape(corner))
                .background(Color.DarkGray)
                .clickable {
                    expanded = !expanded
                },
        )
    }
}
