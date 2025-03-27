package com.wyekings.composable.compose.sideeffects.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import timber.log.Timber

@Composable
fun SnapshotFlowPage() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        val lazyListState = rememberLazyListState()
        LazyColumn(state = lazyListState) {
            val items = (0..50).map { "item $it" }
            items(items) {
                Text(text = it, modifier = Modifier.height(40.dp))
                HorizontalDivider(thickness = 1.dp)
            }
        }

        val context = LocalContext.current
        LaunchedEffect(lazyListState) {
            snapshotFlow { lazyListState.firstVisibleItemIndex }
                .map { index -> index > 0 }
                .distinctUntilChanged()
                .filter { it }
                .collectLatest {
                    Toast.makeText(context,"Scrolls past the first item",Toast.LENGTH_LONG).show()
                    Timber.d("Scrolls past the first item")
                }
        }
    }
}

@Preview
@Composable
private fun SnapshotFlowPagePreview() {
    SnapshotFlowPage()
}