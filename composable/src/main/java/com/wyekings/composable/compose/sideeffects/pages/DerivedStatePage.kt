package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DerivedStatePage() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        val lazyListState = rememberLazyListState()

        LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
            val items = (0..50).map { "item $it" }
            items(items) {
                Text(text = it, modifier = Modifier.height(40.dp))
                HorizontalDivider(thickness = 1.dp)
            }
        }

        val showButton by remember {
            derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
        }

        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier
                .padding(bottom = 30.dp, end = 30.dp)
                .align(Alignment.BottomEnd),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val scope = rememberCoroutineScope()
            Button(
                onClick = {
                    scope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "top")
            }
        }
    }
}

@Preview
@Composable
private fun DerivedStatePagePreview() {
    DerivedStatePage()
}