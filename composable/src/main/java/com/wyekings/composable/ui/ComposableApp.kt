package com.wyekings.composable.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.wyekings.composable.compose.modifier.ModifierScreen


@Composable
fun ComposableApp() {
    val navController = rememberNavController()
    ComposableNavHost(navController)
}

@Composable
fun ComposableNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "composable", enterTransition = {
        fadeIn(
            tween(
                300, easing = LinearEasing
            )
        ) + slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(300, easing = EaseIn)
        )
    }, exitTransition = {
        fadeOut(
            tween(
                300, easing = LinearEasing
            )
        )
//        + slideOutOfContainer(
//            towards = AnimatedContentTransitionScope.SlideDirection.Start,
//            animationSpec = tween(300, easing = EaseOut)
//        )
    }, popEnterTransition = {
        fadeIn(
            tween(
                300, easing = LinearEasing
            )
        )
//        + slideIntoContainer(
//            towards = AnimatedContentTransitionScope.SlideDirection.End,
//            animationSpec = tween(300, easing = EaseIn)
//        )
    }, popExitTransition = {
        fadeOut(
            tween(
                300, easing = LinearEasing
            )
        ) + slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            animationSpec = tween(300, easing = EaseOut)
        )
    }) {
        composable("composable") {
            ComposableScreen {
                navController.navigate(it.route)
            }
        }
        composable("text") {
            TextScreen()
        }
        composable("image") {
            ImageScreen()
        }
        composable("button") {
            ButtonScreen()
        }
        composable("tab_row") {
            TabRowScreen()
        }
        composable("animatable") {
            AnimatableScreen()
        }
        composable("bounce") {
            BounceScreen()
        }
        composable("transition") {
            TransitionScreen()
        }
        composable("transition") {
            TransitionScreen()
        }

        composable("animatedVisibility") {
            AnimatedVisibilityScreen()
        }

        composable("animation") {
            AnimationScreen()
        }
        composable("modifier") {
            ModifierScreen()
        }
    }
}