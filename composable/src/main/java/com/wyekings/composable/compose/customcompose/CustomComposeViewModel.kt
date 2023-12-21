package com.wyekings.composable.compose.customcompose

import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.customcompose.pages.CustomDrawPage
import com.wyekings.composable.compose.customcompose.pages.CustomLayoutPage
import com.wyekings.composable.compose.customcompose.pages.CustomSubcomposeLayoutPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomComposeViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page(title = "CustomDraw") {
            CustomDrawPage()
        },
        Page(title = "Layout") {
            CustomLayoutPage()
        },
        Page(title = "SubcomposeLayout") {
            CustomSubcomposeLayoutPage()
        },
    ).reversed()
}