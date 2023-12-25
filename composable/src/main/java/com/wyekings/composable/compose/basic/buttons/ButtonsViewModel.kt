package com.wyekings.composable.compose.basic.buttons

import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.basic.buttons.pages.SegmentedButtonsPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ButtonsViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page("SegmentedButtons") {
            SegmentedButtonsPage()
        },
    )
}