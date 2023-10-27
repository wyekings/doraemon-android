package com.wyekings.composable.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wyekings.composable.compose.animations.AnimationsScreen
import com.wyekings.composable.compose.basic.ImageScreen
import com.wyekings.composable.compose.basic.TabRowScreen
import com.wyekings.composable.compose.basic.TextScreen
import com.wyekings.composable.compose.composable.ComposableScreen


@Composable
fun ComposableApp() {
    val navController = rememberNavController()
    ComposableNavHost(navController)
}

@Composable
fun ComposableNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "composable") {
        composable("composable") {
            ComposableScreen {
                navController.navigate(it.route)
            }
        }
        composable("tab_row") {
            TabRowScreen()
        }
        composable("text") {
            TextScreen()
        }
        composable("image") {
            ImageScreen()
        }
        composable("animations") {
            AnimationsScreen()
        }
    }
}