package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun NestedScrollPage() {
    var offsetY by remember { mutableFloatStateOf(0f) }
    val connection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return super.onPreScroll(available, source)
            }

            override fun onPostScroll(
                consumed: Offset, available: Offset, source: NestedScrollSource
            ): Offset {
                offsetY += available.y
                return available
            }
        }
    }
    val dispatcher = remember {
        NestedScrollDispatcher()
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .offset {
            IntOffset(0, offsetY.roundToInt())
        }
        .draggable(state = rememberDraggableState { onDelta ->
            val consumed =
                dispatcher.dispatchPreScroll(Offset(0f, onDelta), NestedScrollSource.Drag)
            offsetY += onDelta - consumed.y
            dispatcher.dispatchPostScroll(
                consumed = Offset(0f, onDelta), available = Offset.Zero, NestedScrollSource.Drag
            )
        }, Orientation.Vertical)
        .nestedScroll(
            connection = connection, dispatcher = null
        )
    ) {
        for (i in 0..5) {
            Text(
                text = "Item：$i",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                textAlign = TextAlign.Center,
            )
        }

        LazyColumn {
            for (i in 0..20) {
                item {
                    Text(
                        text = "LazyColumnItem: $i",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NestedScrollPagePreview() {
    NestedScrollPage()
}