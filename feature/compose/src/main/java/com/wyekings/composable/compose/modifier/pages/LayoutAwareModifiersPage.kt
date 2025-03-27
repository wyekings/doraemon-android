package com.wyekings.composable.compose.modifier.pages

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Preview
@Composable
fun LayoutAwareModifiersPage() {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OnRemasuredModifierSample()
        Spacer(modifier = Modifier.height(10.dp))
        onPlacedSample()
        Spacer(modifier = Modifier.height(10.dp))
        OnGloballyPositionedSample()
    }
}

@Composable
private fun OnGloballyPositionedSample() {
    Box(modifier = Modifier
        .onGloballyPositioned {
            val width = it.size.width
            val height = it.size.height
            val x = it.positionInParent().x
            val y = it.positionInParent().y

            Timber.d("onGloballyPositioned: width=$width height=$height x=$x y=$y")
        }
        .background(Color.Red)
        .size(40.dp))
}

@Composable
private fun onPlacedSample() {
    Box(modifier = Modifier
        .onSizeChanged {
            Timber.d("onSizeChanged1")
        }
        .onPlaced {
            val width = it.size.width
            val height = it.size.height
            val x = it.positionInParent().x
            val y = it.positionInParent().y

            Timber.d("onPlaced3: width=$width height=$height x=$x y=$y")
        }
        .onSizeChanged {
            Timber.d("onSizeChanged2")
        }
        .background(Color.Blue)
        .padding(10.dp)
        .onPlaced {
            val width = it.size.width
            val height = it.size.height
            val x = it.positionInParent().x
            val y = it.positionInParent().y

            Timber.d("onPlaced4: width=$width height=$height x=$x y=$y")
        }
        .background(Color.Red)
        .size(40.dp))
}

@Composable
private fun OnRemasuredModifierSample() {
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