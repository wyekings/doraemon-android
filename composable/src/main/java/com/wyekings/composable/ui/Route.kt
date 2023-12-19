package com.wyekings.composable.ui

import androidx.compose.runtime.Composable

data class Route(
    val route: String,
    val content: @Composable (block: ((Route) -> Unit)?) -> Unit,
)