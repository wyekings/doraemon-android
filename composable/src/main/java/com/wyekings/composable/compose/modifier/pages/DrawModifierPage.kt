package com.wyekings.composable.compose.modifier.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun DrawModifierPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier
            .drawWithContent {
                drawRect(Color.Red)
                drawContent()
                drawCircle(Color.Yellow)
            }
            .size(40.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(60.dp)
                .padding(10.dp)
                .background(Color.Yellow)
        )
    }
}