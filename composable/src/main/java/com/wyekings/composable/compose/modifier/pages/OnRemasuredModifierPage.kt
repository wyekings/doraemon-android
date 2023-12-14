package com.wyekings.composable.compose.modifier.pages

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.OnRemeasuredModifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import timber.log.Timber

@Preview
@Composable
fun OnRemeasuredModifierPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var toggle by remember { mutableStateOf(false) }
        val size by animateDpAsState(targetValue = if (toggle) 200.dp else 100.dp, label = "toggle")
        var changedSize by remember { mutableStateOf(IntSize.Zero) }

        Box(modifier = Modifier
            .background(Color.Yellow)
            .onSizeChanged {
                changedSize = it
            }
            .size(size), contentAlignment = Alignment.Center) {
            Text(text = "size=$changedSize")
        }

        Button(onClick = { toggle = !toggle }) {
            Text(text = "change size")
        }

        // 3、4 fist，1、2 later
        Box(modifier = Modifier
            .background(Color.Red)
            .onSizeChanged {
                Timber.d("onSizeChanged1=$it")
            }
            .onSizeChanged {
                Timber.d("onSizeChanged2=$it")
            }
            .padding(10.dp)
            .onSizeChanged {
                Timber.d("onSizeChanged3=$it")
            }
            .onSizeChanged {
                Timber.d("onSizeChanged4=$it")
            }
            .padding(20.dp)
            .size(50.dp))

        Modifier.then(object : OnRemeasuredModifier {
            override fun onRemeasured(size: IntSize) {

            }
        })
    }
}
