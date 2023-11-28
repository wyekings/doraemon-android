package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.wyekings.common.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Preview
@Composable
fun AnimateVectorPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val image =
            AnimatedImageVector.animatedVectorResource(id = R.drawable.ic_hourglass_animated)

        var atEnd by remember {
            mutableStateOf(false)
        }
        Image(
            painter = rememberAnimatedVectorPainter(animatedImageVector = image, atEnd = atEnd),
            contentDescription = "Timer",
            modifier = Modifier.clickable {
                atEnd = !atEnd
            },
            contentScale = ContentScale.Crop
        )
    }
}