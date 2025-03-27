package com.wyekings.composable.compose.modifier

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.wyekings.composable.ui.TabPager

@Composable
fun ModifierScreen(viewModel: ModifierViewModel = hiltViewModel()) {
    val pages = viewModel.pages
    TabPager(pages = pages)
}