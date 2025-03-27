package com.wyekings.composable.compose.animations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.wyekings.composable.ui.TabPager

@Composable
fun AnimationScreen(viewModel: AnimationViewModel = hiltViewModel()) {
    val pages = viewModel.pages
    TabPager(pages = pages)
}