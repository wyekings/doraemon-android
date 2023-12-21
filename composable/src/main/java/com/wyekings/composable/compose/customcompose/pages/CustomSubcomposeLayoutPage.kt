package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber
import kotlin.math.max

@Composable
fun CustomSubcomposeLayoutPage() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.size(120.dp)) {
            BoxWithConstraintsSample()
        }

        Box(modifier = Modifier.size(100.dp)) {
            BoxWithConstraintsSample()
        }

        Box(modifier = Modifier.size(80.dp)) {
            CustomAdaptiveSubcomposeLayout {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .size(20.dp)
                )
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .size(20.dp)
                )
            }
        }

        Box(modifier = Modifier.size(60.dp)) {
            CustomAdaptiveSubcomposeLayout {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .size(20.dp)
                )
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun BoxWithConstraintsSample() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        if (minWidth > 110.dp) {
            RowBoxes()
        } else {
            ColumnBoxed()
        }
    }
}

@Composable
private fun ColumnBoxed() {
    Column {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(40.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .size(40.dp)
        )
    }
}

@Composable
private fun RowBoxes() {
    Row {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(40.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .size(40.dp)
        )
    }
}

@Composable
fun CustomAdaptiveSubcomposeLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val measurables = subcompose(slotId = Unit, content = content)
        var width = 0
        var height = 0
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints).also { placeable ->
                width += placeable.width
                height += placeable.height
            }
        }
        layout(width, height) {
            val minWidth = constraints.maxWidth.toDp()
            val isTablet = minWidth > 70.dp
            var totalWidth = 0
            var totalHeight = 0
            placeables.forEach { placeable ->
                if (isTablet) {
                    placeable.placeRelative(totalWidth ,0)
                    totalWidth += placeable.width
                } else {
                    placeable.placeRelative(0, totalHeight)
                    totalHeight += placeable.height
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomSubcomposePagePreview() {
    CustomSubcomposeLayoutPage()
}