package com.wyekings.composable.compose.sideeffects

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.wyekings.composable.ui.TabPager

@Composable
fun SideEffectsScreen(viewModel: SideEffectsViewModel = hiltViewModel()) {
    TabPager(pages = viewModel.pages)
}