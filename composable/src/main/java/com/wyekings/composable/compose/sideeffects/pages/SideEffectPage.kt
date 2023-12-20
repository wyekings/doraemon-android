package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun SideEffectPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var count by remember { mutableIntStateOf(0) }
        Button(onClick = {
            count++
        }) {
            Text(text = "Count=$count")

            SideEffect {
                Timber.d("SideEffect2")
            }

            Timber.d("SideEffect1")
        }
    }
}

@Preview
@Composable
private fun SideEffectPagePreview() {
    SideEffectPage()
}

