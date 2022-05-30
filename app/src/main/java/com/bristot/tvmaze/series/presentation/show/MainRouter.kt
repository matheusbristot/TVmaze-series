package com.bristot.tvmaze.series.presentation.show

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bristot.tvmaze.series.presentation.nav.Screen

fun NavGraphBuilder.MainRouter(navController: NavHostController) {
    composable(route = Screen.Main.route) {
        MainScreen(navController = navController)
    }
}