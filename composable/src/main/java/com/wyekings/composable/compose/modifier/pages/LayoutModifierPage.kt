package com.wyekings.composable.compose.modifier.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Preview
@Composable
fun LayoutModifierPage() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.background(Color.Yellow)) {
            Text(text = "CustomPadding", modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints = constraints)
                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
//                    placeable.place(0, 0)
                }
            })
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.background(Color.Red)) {
            Text(text = "CustomPadding", modifier = Modifier.layout { measurable, constraints ->
                val padding = 10.dp.roundToPx()
                val paddingConstraints = constraints.copy(
                    maxWidth = constraints.maxWidth - padding * 2,
                    maxHeight = constraints.maxHeight - padding * 2
                )
                val original = measurable.measure(constraints = constraints)
                val placeable = measurable.measure(constraints = paddingConstraints)
                layout(placeable.width + padding * 2, placeable.height + padding * 2) {
                    placeable.placeRelative(padding, padding)
                }
            })
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.background(Color.Red)) {
            Text(text = "Square", modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val size = kotlin.math.max(placeable.width, placeable.height)
                layout(size, size) {
                    placeable.placeRelative(0, 0)
                }
            })
        }
    }
}