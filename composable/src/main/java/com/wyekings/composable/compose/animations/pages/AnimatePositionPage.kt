package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun AnimatePositionPage() {
    Column(modifier = Modifier.fillMaxSize()) {

        var moved by remember {
            mutableStateOf(false)
        }

        val pxToMove = with(LocalDensity.current) {
            100.dp.toPx().toInt()
        }

        val offset by animateIntOffsetAsState(
            targetValue = if (moved) IntOffset(pxToMove, pxToMove) else IntOffset.Zero,
            label = "offset",
        )

        Box(
            modifier = Modifier
                .offset {
                    offset
                }
                .size(150.dp)
                .background(Color.Red)
                .clickable {
                    moved = !moved
                },
        )

        var moved1 by remember {
            mutableStateOf(false)
        }

        val intOffset = if (moved1) IntOffset(pxToMove, pxToMove) else IntOffset.Zero

        val layoutOffset by animateIntOffsetAsState(
            targetValue = if (moved1) IntOffset(pxToMove, pxToMove) else IntOffset.Zero,
            label = "offset",
        )

        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .layout { measurable, constraints ->
                    val offsetValue = if (isLookingAhead) intOffset else layoutOffset
                    val placeable = measurable.measure(constraints = constraints)
                    layout(placeable.width + offsetValue.x, placeable.height + offsetValue.y) {
                        placeable.placeRelative(offsetValue)
                    }
                }
                .size(150.dp)
                .background(Color.Yellow)
                .clickable {
                    moved1 = !moved1
                },
        )


        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .size(150.dp)
                .background(Color.Green)
                .clickable {

                },
        )

    }
}
