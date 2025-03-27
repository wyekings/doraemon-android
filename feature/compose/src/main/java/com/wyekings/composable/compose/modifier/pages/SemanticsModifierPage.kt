package com.wyekings.composable.compose.modifier.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SemanticsModifierPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier
            .background(Color.Red)
            .semantics(mergeDescendants = true) {
                contentDescription = "outer box"
            }
            .size(150.dp), contentAlignment = Alignment.Center) {
            Text(text = "semantics")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier
            .background(Color.Red)
            .clearAndSetSemantics {
                contentDescription = "outer box"
            }
            .size(200.dp), contentAlignment = Alignment.Center) {
            Text(text = "clearAndSetSemantics")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "test", modifier = Modifier.semantics(mergeDescendants = true) { })
        }
    }
}