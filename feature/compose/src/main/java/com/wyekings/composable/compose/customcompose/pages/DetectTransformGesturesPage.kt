package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import timber.log.Timber
import kotlin.math.roundToInt

@Composable
fun DetectTransformGesturesPage() {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var zoomState by remember { mutableFloatStateOf(1.0f) }
    var rotationState by remember { mutableFloatStateOf(0f) }
    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTransformGestures { centroid, pan, zoom, rotation ->
                offset += pan
                zoomState += zoom
                rotationState += rotation
                Timber.d("pan=$offset zoom=$zoomState rotation=$rotationState")
            }
        }) {
        Box(modifier = Modifier
            .background(Color.Red)
            .graphicsLayer {
                scaleX = zoomState
                scaleY = zoomState
            }
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .rotate(rotationState)
            .size(80.dp))
    }
}

@Preview
@Composable
private fun DetectTransformGesturesPagePreview() {
    DetectTransformGesturesPage()
}