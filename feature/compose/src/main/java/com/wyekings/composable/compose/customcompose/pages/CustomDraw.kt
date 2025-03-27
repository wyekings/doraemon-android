package com.wyekings.composable.compose.customcompose.pages

import android.graphics.Camera
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.wyekings.common.R
import kotlin.math.roundToInt

@Composable
fun CustomDrawPage() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DrawBehindSample()
        Spacer(modifier = Modifier.height(10.dp))
        DrawWithContentSample()
        Spacer(modifier = Modifier.height(10.dp))
        RotateCanvasSample()
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedRotationSample()
        Spacer(modifier = Modifier.height(10.dp))
        NativeCanvasSample()
    }
}

@Composable
private fun NativeCanvasSample() {
    Box(modifier = Modifier.padding(10.dp)) {
        val avatarImage = ImageBitmap.imageResource(R.drawable.ic_avatar)
        val camera = remember { Camera() }
        val paint = remember { Paint() }
        val rotationAnimatable = remember { Animatable(0f) }
        LaunchedEffect(Unit) {
            rotationAnimatable.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 2000, easing = LinearEasing)
                ),
            )
        }
        Canvas(
            modifier = Modifier.size(80.dp),
        ) {
            val dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
            drawIntoCanvas {canvas ->
                canvas.translate(size.width / 2, size.height / 2)
                canvas.rotate(-45f)
                camera.save()
                camera.rotateX(rotationAnimatable.value)
                camera.applyToCanvas(canvas.nativeCanvas)
                camera.restore()
                canvas.rotate(45f)
                canvas.translate(-size.width / 2, -size.height / 2)
                canvas.drawImageRect(
                    avatarImage, dstSize = dstSize, paint = paint
                )
            }
        }
    }
}

@Composable
private fun AnimatedRotationSample() {
    val rotationAnimatable = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(Unit) {
        rotationAnimatable.animateTo(
            targetValue = 360f, animationSpec = infiniteRepeatable(tween(durationMillis = 2000))
        )
    }
    Box(modifier = Modifier.padding(10.dp)) {
        val avatarImage = ImageBitmap.imageResource(R.drawable.ic_avatar)
        Canvas(
            modifier = Modifier
                .size(80.dp)
                .graphicsLayer {
                    rotationX = rotationAnimatable.value
                },
        ) {
            drawImage(
                image = avatarImage,
                dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
            )
        }
    }
}

@Composable
private fun RotateCanvasSample() {
    Box(modifier = Modifier.padding(10.dp)) {
        val avatarImage = ImageBitmap.imageResource(R.drawable.ic_avatar)
        Canvas(modifier = Modifier.size(80.dp)) {
            drawRect(Color.Yellow)
            rotate(45f) {
                drawImage(
                    image = avatarImage,
                    dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
                )
            }
        }
    }
}

@Composable
private fun DrawWithContentSample() {
    Text(text = "drawWithContent", modifier = Modifier.drawWithContent {
        drawRect(Color.Yellow)
        drawContent()
        drawLine(
            Color.Red,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 2.dp.toPx()
        )
    })
}

@Composable
private fun DrawBehindSample() {
    Text(text = "drawBehind", modifier = Modifier.drawBehind {
        drawRect(Color.Yellow)
    })
}

@Preview
@Composable
private fun CustomDrawPagePreview() {
    CustomDrawPage()
}