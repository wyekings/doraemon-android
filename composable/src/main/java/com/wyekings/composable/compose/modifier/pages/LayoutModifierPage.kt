package com.wyekings.composable.compose.modifier.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LayoutModifierPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.background(Color.Yellow)) {
            Text(text = "CustomPadding", modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints = constraints)
                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
//                    placeable.place(0, 0)
                }
            })
        }

        Spacer(modifier = Modifier.height(5.dp))

        Box(modifier = Modifier.background(Color.Red)) {
            Text(text = "CustomPadding", modifier = Modifier.layout { measurable, constraints ->
                val padding = 10.dp.roundToPx()
                // limit max width when match max width
                val paddingConstraints = constraints.copy(
                    maxWidth = constraints.maxWidth - padding * 2,
                    maxHeight = constraints.maxHeight - padding * 2
                )
                val placeable = measurable.measure(constraints = paddingConstraints)
                layout(placeable.width + padding * 2, placeable.height + padding * 2) {
                    placeable.placeRelative(padding, padding)
                }
            })
        }

        Spacer(modifier = Modifier.height(5.dp))

        Box(modifier = Modifier.background(Color.Red)) {
            Text(text = "Square", modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val size = kotlin.math.max(placeable.width, placeable.height)
                layout(size, size) {
                    placeable.placeRelative(0, 0)
                }
            })
        }

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .size(20.dp)
                .size(40.dp)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "1")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .size(20.dp)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "2")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .size(20.dp)
                .requiredSize(40.dp)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "3")
        }

        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .requiredSize(40.dp)
                .size(20.dp)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "4")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Box(modifier = Modifier.size(50.dp)) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize()
                    .wrapContentSize()
                    .size(20.dp)
                    .background(Color.Yellow),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "5")
            }
        }
        Spacer(modifier = Modifier.height(5.dp))

        Box(modifier = Modifier.size(50.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .size(20.dp)
                    .background(Color.Yellow),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "6")
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "7",
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
                .background(Color.Green)
                .padding(10.dp)
                .background(Color.Red),
        )

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = painterResource(id = com.wyekings.common.R.drawable.ic_tree),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .padding(10.dp)
                .size(100.dp)
        )
    }
}