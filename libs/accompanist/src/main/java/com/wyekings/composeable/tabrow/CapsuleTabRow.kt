package com.wyekings.composeable.tabrow

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.wyekings.composeable.ripple.NoRippleInteractionSource

/**
 *  @author wye on 9/11/23
 */

@Composable
fun CapsuleTabRow(
    selectedTabIndex: Int,
    tabs: List<String>,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    rowContainerColor: Color = Color.DarkGray,
    tabContainerColor: Color = Color.Yellow,
    normalTexTColor: Color = Color.White,
    selectedTextColor: Color = Color.Black,
    onTabSelected: (index: Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = rowContainerColor,
        modifier = modifier
            .clip(RoundedCornerShape(50)),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .padding(1.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(50)),
                color = tabContainerColor
            )
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == selectedTabIndex
            val textColor = remember { Animatable(normalTexTColor) }
            LaunchedEffect(selected) {
                textColor.animateTo(if (selected) selectedTextColor else normalTexTColor)
            }

            Tab(
                selected = index == selectedTabIndex,
                onClick = {
                    onTabSelected.invoke(index)
                },
                text = {
                    Text(
                        text = tab,
                        modifier = textModifier,
                        color = textColor.value,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                    )
                },
                modifier = Modifier.zIndex(1f),
                interactionSource = NoRippleInteractionSource()
            )
        }
    }
}

@Preview
@Composable
private fun RoundedTabRowPreview() {
    CapsuleTabRow(
        selectedTabIndex = 1,
        tabs = listOf("Active", "Completed"),
    ) {

    }
}