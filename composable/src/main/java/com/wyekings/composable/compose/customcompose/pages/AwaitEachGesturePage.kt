package com.wyekings.composable.compose.customcompose.pages

import android.widget.Toast
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AwaitEachGesturePage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "custom click 1", modifier = Modifier.customClick1 {
            Toast.makeText(context, "custom click 1", Toast.LENGTH_LONG).show()
        })

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "custom click 2", modifier = Modifier.customClick2 {
            Toast.makeText(context, "custom click 2", Toast.LENGTH_LONG).show()
        })

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "on event test", modifier = Modifier.onEvent(onPressed = {
            Toast.makeText(context, "on pressed", Toast.LENGTH_LONG).show()
        }, onMoved = {
            Toast.makeText(context, "on moved", Toast.LENGTH_LONG).show()
        }, onUp = {
            Toast.makeText(context, "on up", Toast.LENGTH_LONG).show()
        }, onCancel = {
            Toast.makeText(context, "on cancel", Toast.LENGTH_LONG).show()
        }))

//        Text(text = "AwaitEachGesture", modifier.pointerInput(Unit) {
//            awaitEachGesture {
////                val initialEvent = awaitPointerEvent(PointerEventPass.Initial)
////                val mainEvent = awaitPointerEvent(PointerEventPass.Main)
////                val finalEvent = awaitPointerEvent(PointerEventPass.Final)
//                while (true) {
//                    val event = awaitPointerEvent()
//                    Timber.d("event=${event.type}")
//                }
////                awaitFirstDown()
////                waitForUpOrCancellation()
//            }
//        })
    }
}

private fun Modifier.customClick1(onClick: () -> Unit) = this then pointerInput(Unit) {
    awaitEachGesture {
        while (true) {
            val event = awaitPointerEvent()
            if (event.type == PointerEventType.Move) {
                val pos = event.changes[0].position
                if (pos.x < 0 || pos.x > size.width || pos.y < 0 || pos.y > size.height) {
                    break
                }
            } else if (event.type == PointerEventType.Release && event.changes.size == 1) {
                onClick()
                break
            }
        }
    }
}

private fun Modifier.customClick2(onClick: () -> Unit) = this then pointerInput(Unit) {
    awaitEachGesture {
        waitForUpOrCancellation()?.let {
            onClick()
        }
    }
}

private fun Modifier.onEvent(
    onPressed: () -> Unit, onMoved: () -> Unit, onUp: () -> Unit, onCancel: () -> Unit
) = this then pointerInput(Unit) {
    awaitEachGesture {
        awaitFirstDown()
        onPressed()

        while (true) {
            val event = awaitPointerEvent()
            if (event.type == PointerEventType.Move) {
                onMoved()
                break
            }
        }

        val change = waitForUpOrCancellation()
        if (change != null) {
            onUp()
        } else {
            onCancel()
        }
    }
}

@Preview
@Composable
private fun AwaitEachGesturePagePreview() {
    AwaitEachGesturePage()
}
