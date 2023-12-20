package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun DisposableEffectPage() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var showBox by remember {
            mutableStateOf(true)
        }

        if (showBox) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .size(50.dp)
            ) {
                DisposableEffect(Unit) {
                    Timber.d("disposableEffect1")
                    onDispose {
                        Timber.d("disposableEffect2")
                    }
                }
            }
        }

        DisposableEffect(showBox) {
            Timber.d("disposableEffect3")
            onDispose {
                Timber.d("disposableEffect4")
            }
        }

        DisposableEffect(Unit) {
            Timber.d("disposableEffect5")
            onDispose {
                Timber.d("disposableEffect6")
            }
        }

        Button(onClick = {
            showBox = !showBox
        }) {
            Text("ShowBox")
        }
    }
}

@Preview
@Composable
private fun DisposableEffectPagePreview() {
    DisposableEffectPage()
}

