package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun CustomLayoutPage() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CustomColumn {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .size(80.dp)
            )
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .size(80.dp)
            )
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .size(80.dp)
            )
        }
    }
}

@Composable
fun CustomColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        var width = 0
        var height = 0
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints).also { placeable ->
                width = max(placeable.width, width)
                height += placeable.height
            }
        }
        var totalHeight = 0
        layout(width, height) {
            placeables.forEach { placeable ->
                placeable.placeRelative(0, totalHeight)
                totalHeight += placeable.height
            }
        }
    }
}

@Preview
@Composable
private fun CustomLayoutPagePreview() {
    CustomLayoutPage()
}