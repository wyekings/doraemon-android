package com.wyekings.composable.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wyekings.composable.compose.home.HomeScreen
import com.wyekings.composable.compose.image.ImageScreen


@Composable
fun ComposableApp() {
    val navController = rememberNavController()
    ComposableNavHost(navController)
}

@Composable
fun ComposableNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen {
                navController.navigate(it.route)
            }
        }
        composable("image") {
            ImageScreen()
        }
    }
}