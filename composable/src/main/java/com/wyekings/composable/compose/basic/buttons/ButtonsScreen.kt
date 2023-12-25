package com.wyekings.composable.compose.basic.buttons

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.wyekings.composable.ui.TabPager

@Composable
fun ButtonsScreen(viewModel: ButtonsViewModel = hiltViewModel()) {
    TabPager(pages = viewModel.pages)
}