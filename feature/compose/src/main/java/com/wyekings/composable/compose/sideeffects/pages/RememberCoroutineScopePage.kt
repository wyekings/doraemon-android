package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopePage() {
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = hostState)

        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                scope.launch {
                    hostState.showSnackbar(message = "SnackBar")
                }
            }, text = { Text(text = "SnackBar") }, icon = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "snackBar")
            }, shape = CircleShape)
        },
    ) {
        Box(modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
private fun RememberCoroutineScopePagePreview() {
    RememberCoroutineScopePage()
}

