package com.wyekings.composable.compose.basic.buttons.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class SegmentedButton(
    val text: String,
    val checked: Boolean,
)

@Composable
fun SegmentedButtonsPage() {
    val segmentedButtons = listOf(
        SegmentedButton("Songs", true),
        SegmentedButton("Albums", false),
        SegmentedButton("Podcasts", false),
    )
    SegmentedButtons(buttons = segmentedButtons, onClick = { index, segmentedButton ->

    })
}

@Composable
fun SegmentedButtons(
    buttons: List<SegmentedButton>,
    onClick: (Int, SegmentedButton) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(0.5.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(100.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        buttons.forEachIndexed { index, segmentedButton ->
            val animatedColor by animateColorAsState(
                targetValue = if (segmentedButton.checked) {
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                } else {
                    MaterialTheme.colorScheme.background
                }, label = "button background"
            )

            val shape = if (index == 0 && index == buttons.lastIndex) {
                RoundedCornerShape(100.dp)
            } else if (index == 0) {
                RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp)
            } else if (index == buttons.lastIndex) {
                RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)
            } else {
                RoundedCornerShape(0.dp)
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(color = animatedColor, shape = shape)
                    .clickable {
                        onClick(index, segmentedButton)
                    }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AnimatedVisibility(visible = segmentedButton.checked) {
                    Row {
                        Icon(imageVector = Icons.Rounded.Check, contentDescription = "lead icon")
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Text(
                    text = segmentedButton.text,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            if (index < buttons.lastIndex) {
                VerticalDivider(
                    thickness = 0.5.dp,
                    modifier = Modifier.height(40.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Preview
@Composable
private fun SegmentedButtonsPagePreview() {
    SegmentedButtonsPage()
}