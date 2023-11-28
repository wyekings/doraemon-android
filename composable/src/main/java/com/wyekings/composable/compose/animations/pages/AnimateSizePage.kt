package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimateSizePage() {
    Column(modifier = Modifier.fillMaxSize()) {

        var expanded by remember {
            mutableStateOf(false)
        }

        val interactionSource = remember {
            MutableInteractionSource()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .height(if (expanded) 150.dp else 100.dp)
                .background(Color.Red)
                .clickable(interactionSource = interactionSource, indication = null) {
                    expanded = !expanded
                },
        )


        var expanded2 by remember {
            mutableStateOf(false)
        }
        val height by animateDpAsState(
            targetValue = if (expanded2) 150.dp else 100.dp,
            label = "expend2",
            animationSpec = spring()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(height = height)
                .background(Color.Yellow)
                .clickable(interactionSource = interactionSource, indication = null) {
                    expanded2 = !expanded2
                },
        )
    }
}
