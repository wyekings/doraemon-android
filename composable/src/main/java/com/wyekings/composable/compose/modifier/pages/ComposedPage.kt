package com.wyekings.composable.compose.modifier.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ComposedPage() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val modifier = Modifier.removePaddingWhenClicked()
        Text(
            text = "Text1", modifier = Modifier
                .background(Color.Yellow)
                .then(modifier)
        )
        Text(
            text = "Text2", modifier = Modifier
                .background(Color.Red)
                .then(modifier)
        )
        Spacer(modifier = Modifier.height(20.dp))

        val composedModifier = Modifier.removePaddingWhenClickedByComposed()
        Text(
            text = "Text3", modifier = Modifier
                .background(Color.Yellow)
                .then(composedModifier)
        )
        Text(
            text = "Text4", modifier = Modifier
                .background(Color.Red)
                .then(composedModifier)
        )
    }
}

@Composable
private fun Modifier.removePaddingWhenClicked(): Modifier {
    var padding by remember {
        mutableStateOf(8.dp)
    }
    return this then Modifier
        .padding(padding)
        .clickable { padding = 0.dp }
}

// no @Composable
private fun Modifier.removePaddingWhenClickedByComposed() = composed {
    var padding by remember {
        mutableStateOf(8.dp)
    }
    this then padding(padding).clickable {
        padding = 0.dp
    }
}
