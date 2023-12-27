package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollablePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        var offsetY by remember { mutableIntStateOf(0) }
        Text(
            text = "Scroll me vertical",
            modifier = Modifier
                .background(Color.Yellow)
                .scrollable(
                    state = rememberScrollableState { consumeScrollDelta ->
                        offsetY += consumeScrollDelta.roundToInt()
                        consumeScrollDelta
                    },
                    Orientation.Vertical,
                    overscrollEffect = ScrollableDefaults.overscrollEffect(),
                    enabled = true,
                    reverseDirection = false,
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                    interactionSource = interactionSource,
                    bringIntoViewSpec = ScrollableDefaults.bringIntoViewSpec(),
                )
                .offset {
                    IntOffset(0, offsetY)
                },
        )
    }
}

@Preview
@Composable
private fun ScrollablePagePreview() {
    ScrollablePage()
}