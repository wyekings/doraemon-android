package com.wyekings.sunflower.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wyekings.sunflower.theme.SunflowerTheme
import com.wyekings.sunflower.ui.home.HomeScreen

/**
 *  @author wye on 9/7/23
 */

@Composable
fun SunflowerApp() {
    SunflowerTheme {
        val navController = rememberNavController()
        SunflowerNavHost(navController)
    }
}

@Composable
fun SunflowerNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home" ) {
        composable("home"){
            HomeScreen()
        }
    }
}
