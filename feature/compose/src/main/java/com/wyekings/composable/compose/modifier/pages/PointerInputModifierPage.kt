package com.wyekings.composable.compose.modifier.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputModifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PointerInputModifierPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current
        Text(
            text = "clickable",
            modifier = Modifier.clickable {
                toast(context, "click")
            },
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "combined clickable",
            modifier = Modifier.combinedClickable(onDoubleClick = {
                toast(context, "double click")
            }, onLongClick = {
                toast(context, "long click")
            }) {
                toast(context, "click")
            },
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "detect tap gestures", modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onDoubleTap = {
                toast(context = context, "on double tap")
            }, onLongPress = {
                toast(context = context, "on long press")
            }, onPress = {
                toast(context = context, "on press")
            }, onTap = {
                toast(context = context, "on tap")
            })
        })

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "await each gesture", modifier = Modifier.pointerInput(Unit) {
            awaitEachGesture {

            }
        })
    }
}

private fun toast(context: Context, text: CharSequence) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}