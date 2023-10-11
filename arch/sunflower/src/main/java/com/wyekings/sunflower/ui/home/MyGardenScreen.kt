package com.wyekings.sunflower.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 *  @author wye on 9/7/23
 */

@Composable
@Stable
fun MyGardenScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "My Garden", modifier = Modifier.align(Alignment.Center))
    }
}
