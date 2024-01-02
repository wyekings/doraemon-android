package com.wyekings.composable.compose.customcompose

import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.customcompose.pages.AwaitEachGesturePage
import com.wyekings.composable.compose.customcompose.pages.CustomDrawPage
import com.wyekings.composable.compose.customcompose.pages.CustomLayoutPage
import com.wyekings.composable.compose.customcompose.pages.CustomSubcomposeLayoutPage
import com.wyekings.composable.compose.customcompose.pages.DetectDragGesturesPage
import com.wyekings.composable.compose.customcompose.pages.DetectTransformGesturesPage
import com.wyekings.composable.compose.customcompose.pages.DraggablePage
import com.wyekings.composable.compose.customcompose.pages.LookaheadScopePage
import com.wyekings.composable.compose.customcompose.pages.NestedScrollPage
import com.wyekings.composable.compose.customcompose.pages.ScrollablePage
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
        Page(title = "LookaheadScope") {
            LookaheadScopePage()
        },
        Page(title = "Draggable") {
            DraggablePage()
        },
        Page(title = "Scrollable") {
            ScrollablePage()
        },
        Page(title = "NestedScroll") {
            NestedScrollPage()
        },
        Page(title = "DetectDragGestures") {
            DetectDragGesturesPage()
        },
        Page(title = "DetectTransformGestures") {
            DetectTransformGesturesPage()
        },
        Page(title = "AwaitEachGesture") {
            AwaitEachGesturePage()
        },
    ).reversed()
}