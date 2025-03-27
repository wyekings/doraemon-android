package com.wyekings.composable.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun ComposableApp() {
    val navController = rememberNavController()
    ComposableNavHost(navController)
}

@Composable
fun ComposableNavHost(
    navController: NavHostController, viewModel: ComposableViewModel = hiltViewModel()
) {
    NavHost(navController = navController,
        startDestination = viewModel.routes[0].route,
        enterTransition = {
            fadeIn(
                tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300, easing = EaseIn)
            )
        },
        exitTransition = {
            fadeOut(
                tween(
                    300, easing = LinearEasing
                )
            )
//        + slideOutOfContainer(
//            towards = AnimatedContentTransitionScope.SlideDirection.Start,
//            animationSpec = tween(300, easing = EaseOut)
//        )
        },
        popEnterTransition = {
            fadeIn(
                tween(
                    300, easing = LinearEasing
                )
            )
//        + slideIntoContainer(
//            towards = AnimatedContentTransitionScope.SlideDirection.End,
//            animationSpec = tween(300, easing = EaseIn)
//        )
        },
        popExitTransition = {
            fadeOut(
                tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300, easing = EaseOut)
            )
        }) {

        viewModel.routes.forEachIndexed { index, route ->
            composable(route = route.route) {
                when (index) {
                    0 -> route.content {
                        navController.navigate(it.route)
                    }

                    else -> route.content(null)
                }
            }
        }
    }
}