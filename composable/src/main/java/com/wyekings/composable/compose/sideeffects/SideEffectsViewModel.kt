package com.wyekings.composable.compose.sideeffects

import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.sideeffects.pages.DerivedStatePage
import com.wyekings.composable.compose.sideeffects.pages.DisposableEffectPage
import com.wyekings.composable.compose.sideeffects.pages.LaunchedEffectPage
import com.wyekings.composable.compose.sideeffects.pages.ProduceStatePage
import com.wyekings.composable.compose.sideeffects.pages.RememberCoroutineScopePage
import com.wyekings.composable.compose.sideeffects.pages.RememberUpdatedStatePage
import com.wyekings.composable.compose.sideeffects.pages.SideEffectPage
import com.wyekings.composable.compose.sideeffects.pages.SnapshotFlowPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SideEffectsViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page("SideEffect") {
            SideEffectPage()
        },
        Page("DisposableEffect") {
            DisposableEffectPage()
        },
        Page("LaunchedEffect") {
            LaunchedEffectPage()
        },
        Page("RememberUpdatedState") {
            RememberUpdatedStatePage()
        },
        Page("DerivedState") {
            DerivedStatePage()
        },
        Page("rememberCoroutineScope") {
            RememberCoroutineScopePage()
        },
        Page("ProduceState") {
            ProduceStatePage()
        },
        Page("SnapshotFlow") {
            SnapshotFlowPage()
        },
    ).reversed()
}