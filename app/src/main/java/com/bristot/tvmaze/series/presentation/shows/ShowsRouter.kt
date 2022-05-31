package com.bristot.tvmaze.series.presentation.shows

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bristot.tvmaze.series.presentation.nav.Screen

fun NavGraphBuilder.ShowsRouter(navController: NavHostController) {
    composable(route = Screen.Shows.route) {
        ShowsScreen(navController = navController)
    }
}