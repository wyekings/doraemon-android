package com.wyekings.composable.compose.modifier.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.modifier.ModifierLocalProvider
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.modifier.ProvidableModifierLocal
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun ModifierLocalPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Sample1()
        Spacer(modifier = Modifier.height(10.dp))
        Sample2()
        Spacer(modifier = Modifier.height(10.dp))
        Sample3()
        Spacer(modifier = Modifier.height(10.dp))
        Sample4()
        Spacer(modifier = Modifier.height(10.dp))
        Sample5()
        Spacer(modifier = Modifier.height(10.dp))
        Sample6()

        Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    }
}

@Composable
private fun Sample6() {
    val sizeModifierLocal = remember {
        modifierLocalOf { intArrayOf(0, 0) }
    }
    Box(modifier = Modifier
        .then(object : LayoutModifier, ModifierLocalProvider<IntArray> {

            private val size = intArrayOf(0, 0)

            override fun MeasureScope.measure(
                measurable: Measurable, constraints: Constraints
            ): MeasureResult {
                val placeable = measurable.measure(constraints = constraints)
                size[0] = placeable.width
                size[1] = placeable.height
                return layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
                }
            }

            override val key: ProvidableModifierLocal<IntArray>
                get() = sizeModifierLocal
            override val value: IntArray
                get() = size
        })
        .then(object : LayoutModifier, ModifierLocalConsumer {

            private lateinit var size: IntArray

            override fun MeasureScope.measure(
                measurable: Measurable, constraints: Constraints
            ): MeasureResult {
                val placeable = measurable.measure(constraints = constraints)
                Timber.d("width=${size[0]} height=${size[1]}")
                return layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
                }
            }

            override fun onModifierLocalsUpdated(scope: ModifierLocalReadScope) {
                with(scope) {
                    size = sizeModifierLocal.current
                }
            }
        })
        .background(Color.Yellow)
        .size(50.dp)
    )
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun Sample5() {
    val modifierLocal = remember {
        modifierLocalOf<Sender> {
            // default value
            Sender {
                Timber.d("NoSender provided by parent")
            }
//            error("No sender provided by parent.")
        }
    }

    val context = LocalContext.current

    Box(modifier = Modifier
        .modifierLocalProvider(modifierLocal) {
            Sender {
                Toast
                    .makeText(context, it, Toast.LENGTH_LONG)
                    .show()
            }
        }
        .background(Color.Red)
        .size(100.dp), contentAlignment = Alignment.Center) {

        var sender by remember {
            mutableStateOf<Sender?>(null)
        }

        Text(text = "Sender", modifier = Modifier
            .modifierLocalConsumer {
                sender = modifierLocal.current
            }
            .clickable {
                sender?.sendMessage("Hello World")
            })
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun Sample4() {
    val modifierLocal = remember {
        modifierLocalOf { "Unknown" }
    }

    Box(modifier = Modifier
        .modifierLocalProvider(modifierLocal) {
            "World"
        }
        .background(Color.Red)
        .size(100.dp), contentAlignment = Alignment.Center) {

        var value by remember { mutableStateOf("") }
        val context = LocalContext.current

        Text(text = "Consumer", modifier = Modifier
            .modifierLocalConsumer {
                value = modifierLocal.current
            }
            .clickable {
                Toast
                    .makeText(context, "Hello $value", Toast.LENGTH_LONG)
                    .show()
            })
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun Sample3() {
    val senderModifierLocal = modifierLocalOf<Sender> {
//        error("No sender provided by parent.")
        Sender {
            Timber.d("NoSender provided by parent")
        }
    }
    val context = LocalContext.current

    Box(modifier = Modifier
        .modifierLocalProvider(senderModifierLocal) {
            Sender {
                Toast
                    .makeText(context, it, Toast.LENGTH_LONG)
                    .show()
            }
        }
        .background(Color.Blue)
        .size(50.dp)
        .composed {
            var sender by remember {
                mutableStateOf<Sender?>(null)
            }
            this then Modifier
                .modifierLocalConsumer {
                    sender = senderModifierLocal.current
                }
                .clickable {
                    sender?.sendMessage("Hello World")
                }
        })
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun Sample2() {
    val messageModifierLocal = remember {
        modifierLocalOf { "Unknown" }
    }

    Box(modifier = Modifier
        .modifierLocalProvider(messageModifierLocal) {
            "World"
        }
        .composed {
            var mesage by remember { mutableStateOf("") }
            val context = LocalContext.current
            this then Modifier
                .modifierLocalConsumer {
                    mesage = messageModifierLocal.current
                }
                .clickable {
                    Toast
                        .makeText(context, "Hello $mesage", Toast.LENGTH_LONG)
                        .show()
                }
        }
        .background(Color.Yellow)
        .size(50.dp))
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun Sample1() {
    val modifierLocal = remember { modifierLocalOf { "default" } }
    Box(modifier = Modifier
        .modifierLocalConsumer {
            val value = modifierLocal.current
            Timber.d("ModifierLocal=$value")
        }
        .modifierLocalProvider(modifierLocal) {
            "provided value"
        }
        .modifierLocalConsumer {
            val value = modifierLocal.current
            Timber.d("ModifierLocal=$value")
        }
        .background(Color.Red)
        .size(50.dp))
}

private class Sender(private val onMessageReceived: (String) -> Unit) {
    fun sendMessage(message: String) {
        onMessageReceived(message)
    }
}
