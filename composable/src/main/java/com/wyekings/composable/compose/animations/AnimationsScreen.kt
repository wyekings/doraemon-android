package com.wyekings.composable.compose.animations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wyekings.composable.ui.TopBar

@Composable
fun AnimationsScreen() {
    Scaffold(
        topBar = {
            TopBar(title = "Animations")
        },
    ) {
        Box(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimationsScreenPreview() {
    AnimationsScreen()
}