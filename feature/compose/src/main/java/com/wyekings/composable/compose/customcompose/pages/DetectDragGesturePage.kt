package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetectDragGesturesPage() {

    var changeState by remember { mutableStateOf(Offset.Zero) }
    var dragAmountState by remember { mutableStateOf(Offset.Zero) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    changeState = change.position
                    dragAmountState += dragAmount
                }
//                detectHorizontalDragGestures { change, dragAmount ->  }
//                detectVerticalDragGestures { change, dragAmount ->  }
//                detectDragGesturesAfterLongPress { change, dragAmount ->  }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "change=$changeState")
        Text(text = "total=$dragAmountState")
    }
}

@Preview
@Composable
private fun DetectDragGesturesPagePreview() {
    DetectDragGesturesPage()
}
