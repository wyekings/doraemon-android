package com.wyekings.composable.compose.animations

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
import com.wyekings.composable.compose.animations.pages.RadarLoadingPage
import com.wyekings.composable.compose.animations.pages.RepeatAnimationPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimationViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page(title = "Appear/Disappear", content = { AnimateAppearingDisappearingPage() }),
        Page(title = "AnimateBackground", content = { AnimateBackgroundPage() }),
        Page(title = "AnimateSize", content = { AnimateSizePage() }),
        Page(title = "AnimatePosition", content = { AnimatePositionPage() }),
        Page(title = "AnimateElevation", content = { AnimateElevationPage() }),
        Page(title = "AnimateText", content = { AnimateTextPage() }),
        Page(title = "AnimateContent", content = { AnimateContentPage() }),
        Page(title = "RepeatAnimation", content = { RepeatAnimationPage() }),
        Page(title = "Animation", content = { AnimationPage() }),
        Page(title = "AnimateVector", content = { AnimateVectorPage() }),
        Page(title = "RadarLoading", content = { RadarLoadingPage() }),
    ).reversed()
}