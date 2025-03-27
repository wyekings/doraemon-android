package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun RememberUpdatedStatePage() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var state by remember { mutableStateOf("state") }

        LaunchedEffect(Unit) {
            delay(3000)
            Timber.d("state1=$state")
        }

        OriginalState(state)

        UpdatedState(state)

        Button(onClick = {
            state = "updated state"
        }) {
            Text(text = "update")
        }
    }
}

@Composable
private fun OriginalState(state: String) {
    LaunchedEffect(Unit) {
        delay(3000)
        Timber.d("state2=$state")
    }
}

@Composable
private fun UpdatedState(state: String) {
    val updatedState by rememberUpdatedState(newValue = state)
    LaunchedEffect(Unit) {
        delay(3000)
        Timber.d("state3=$updatedState")
    }
}

@Preview
@Composable
private fun RememberUpdatedStatePagePreview() {
    RememberUpdatedStatePage()
}