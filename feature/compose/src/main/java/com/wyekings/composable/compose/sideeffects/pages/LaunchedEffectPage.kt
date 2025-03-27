package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Composable
fun LaunchedEffectPage() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var big by remember { mutableStateOf(false) }
        val animatable = remember {
            Animatable(50.dp, Dp.VectorConverter, label = "size")
        }

//        val scope = rememberCoroutineScope()
//        var job: Job? = null
//        remember(big) {
//            job?.cancel()
//            job = scope.launch {
//                animatable.animateTo(
//                    if (big) 80.dp else 50.dp, animationSpec = tween(durationMillis = 3000)
//                )
//            }
//        }


        LaunchedEffect(big) {
            animatable.animateTo(
                if (big) 80.dp else 50.dp, animationSpec = tween(durationMillis = 3000)
            )
        }

        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(animatable.value)
        )

        Button(onClick = {
            big = !big
        }) {
            Text("Animate Size")
        }
    }
}

@Preview
@Composable
private fun LaunchedEffectPagePreview() {
    LaunchedEffectPage()
}

