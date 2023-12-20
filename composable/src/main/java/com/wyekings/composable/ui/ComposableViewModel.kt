package com.wyekings.composable.ui

import androidx.compose.material.icons.Icons
import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.animations.AnimatableScreen
import com.wyekings.composable.compose.animations.AnimatedVisibilityScreen
import com.wyekings.composable.compose.animations.AnimationScreen
import com.wyekings.composable.compose.animations.BounceScreen
import com.wyekings.composable.compose.animations.TransitionScreen
import com.wyekings.composable.compose.basic.ButtonScreen
import com.wyekings.composable.compose.basic.ImageScreen
import com.wyekings.composable.compose.basic.TabRowScreen
import com.wyekings.composable.compose.basic.TextScreen
import com.wyekings.composable.compose.composable.ComposableScreen
import com.wyekings.composable.compose.customcompose.CustomComposeScreen
import com.wyekings.composable.compose.modifier.ModifierScreen
import com.wyekings.composable.compose.sideeffects.SideEffectsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComposableViewModel @Inject constructor() : ViewModel() {

    val routes = listOf(Route("Composable") {
        ComposableScreen { route ->
            it?.invoke(route)
        }
    }, Route("Text") {
        TextScreen()
    }, Route("Button") {
        ButtonScreen()
    }, Route("Image") {
        ImageScreen()
    }, Route("TabRow") {
        TabRowScreen()
    }, Route("Animatable") {
        AnimatableScreen()
    }, Route("Bounce") {
        BounceScreen()
    }, Route("AnimatedVisibility") {
        AnimatedVisibilityScreen()
    }, Route("Animation") {
        AnimationScreen()
    }, Route("Transition") {
        TransitionScreen()
    }, Route("Modifier") {
        ModifierScreen()
    }, Route("SideEffects") {
        SideEffectsScreen()
    }, Route("CustomCompose") {
        CustomComposeScreen()
    })

    val composables: List<Route>
        get() = routes.drop(1)

}