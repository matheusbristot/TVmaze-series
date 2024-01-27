package com.bristot.tvmaze.series.presentation.detail.show

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bristot.tvmaze.series.presentation.nav.Screen
import com.bristot.tvmaze.series.series.isInvalidId
import com.bristot.tvmaze.series.series.orInvalidId

private const val idDetail = "idArg"

fun NavGraphBuilder.showDetailRouter(navController: NavHostController) {
    val route = Screen.ShowDetail.route + "/{$idDetail}"
    composable(
        route = route,
        arguments = listOf(
            navArgument(idDetail) {
                type = NavType.LongType
                nullable = false
            }
        )
    ) { entry ->
        val showId = entry.arguments?.getLong(idDetail).orInvalidId()
        if (showId.isInvalidId()) navController.popBackStack()
        else TVShowDetailsScreen(navController, entry.arguments?.getLong(idDetail).orInvalidId())
    }
}