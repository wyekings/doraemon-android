package com.wyekings.composable.compose.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyekings.composable.ui.TopBar

/**
 *  @author wye on 8/9/23
 */

@Preview(showBackground = true)
@Composable
private fun TextSamplePreview() {
    TextScreen()
}

@Composable
fun TextScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "Text")
        CustomText(name = "Compose!")
    }
}

@Composable
fun CustomText(name: String, modifier: Modifier = Modifier) {
    Box {
        Text(text = "Hello $name!",
            modifier = modifier
                .padding(8.dp)
                .background(Color.Yellow)
                .clickable { }
                .padding(8.dp))
    }
}
