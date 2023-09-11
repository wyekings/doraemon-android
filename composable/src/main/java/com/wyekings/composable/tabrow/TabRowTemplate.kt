package com.wyekings.composable.tabrow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyekings.composeable.tabrow.CapsuleTabRow

/**
 *  @author wye on 9/11/23
 */

@Composable
fun TabRowTemplate() {
    Column(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        CapsuleTabRow(selectedTabIndex = selectedTabIndex, modifier = Modifier.padding(horizontal = 8.dp), tabs = listOf("First","Second")){
            selectedTabIndex = it
        }
    }
}

@Preview
@Composable
fun TabRowTemplatePreview() {
    TabRowTemplate()
}