package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun DraggablePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val state = rememberDraggableState { onDelta ->

        }
        val interactionSource = remember { MutableInteractionSource() }
        Text(text = "Modifier.draggable() test", modifier = Modifier
            .draggable(
                state = state,
                Orientation.Horizontal,
                interactionSource = interactionSource,
                onDragStarted = { startedPosition ->
                    Timber.d("onDragStarted=$startedPosition")
                },
                onDragStopped = { velocity ->
                    Timber.d("onDragStopped=$velocity")
                },
                enabled = true,
                startDragImmediately = false,
                reverseDirection = false,
            )
            .offset {
                IntOffset.Zero
            })

        Spacer(modifier = Modifier.height(10.dp))
        val isDragged by interactionSource.collectIsDraggedAsState()
        Text(text = "InteractionSource: isDragged=$isDragged")
    }
}

@Preview
@Composable
private fun DraggablePagePreview() {
    DraggablePage()
}