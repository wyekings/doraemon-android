package com.wyekings.composable.compose.customcompose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.wyekings.composable.ui.TabPager

@Composable
fun CustomComposeScreen(viewModel: CustomComposeViewModel = hiltViewModel()) {
    TabPager(pages = viewModel.pages)
}