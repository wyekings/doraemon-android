package com.wyekings.composable.compose.modifier

import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.animations.pages.AnimateAppearingDisappearingPage
import com.wyekings.composable.compose.animations.pages.AnimateBackgroundPage
import com.wyekings.composable.compose.animations.pages.AnimateContentPage
import com.wyekings.composable.compose.animations.pages.AnimateElevationPage
import com.wyekings.composable.compose.animations.pages.AnimatePositionPage
import com.wyekings.composable.compose.animations.pages.AnimateSizePage
import com.wyekings.composable.compose.animations.pages.AnimateTextPage
import com.wyekings.composable.compose.animations.pages.AnimateVectorPage
import com.wyekings.composable.compose.animations.pages.AnimationPage
import com.wyekings.composable.compose.animations.pages.RepeatAnimationPage
import com.wyekings.composable.compose.modifier.pages.ComposedPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModifierViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page(title = "Composed", content = { ComposedPage() }),
    ).reversed()
}