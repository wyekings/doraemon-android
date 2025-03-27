package com.wyekings.composable.compose.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wyekings.composable.ui.ComposableViewModel
import com.wyekings.composable.ui.Route
import com.wyekings.composable.ui.TopBar
import com.wyekings.composeable.layout.Center

@Composable
fun ComposableScreen(
    viewModel: ComposableViewModel = hiltViewModel(), onSelect: (Route) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Composable")
        },
        modifier = Modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(viewModel.composables, key = { it.route }) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clickable {
                            onSelect(it)
                        },
                    elevation = CardDefaults.cardElevation(2.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
//                    shape = RectangleShape,
                ) {
                    Center(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = it.route,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
