package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyekings.composeable.modifier.noRippleClickable
import com.wyekings.composeable.ripple.rememberNoRippleInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnimateElevationPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mutableInteractionSource = remember {
            MutableInteractionSource()
        }

        val pressed by mutableInteractionSource.collectIsPressedAsState()

        val elevation by animateDpAsState(
            targetValue = if (pressed) 30.dp else 4.dp,
            label = "elevation",
        )

        Box(modifier = Modifier
            .size(100.dp)
            .graphicsLayer {
                shadowElevation = elevation.toPx()
            }
            .noRippleClickable(
                interactionSource = mutableInteractionSource
            ) {

            }
            .background(Color.Red)

        )

        Card(
            onClick = {

            },
            modifier = Modifier
                .padding(top = 40.dp)
                .size(100.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Yellow),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp, pressedElevation = 30.dp
            )
        ) {

        }
    }
}