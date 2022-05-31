package com.bristot.tvmaze.series.presentation.detail.episode

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bristot.tvmaze.series.presentation.nav.Screen
import com.bristot.tvmaze.series.series.isInvalidId
import com.bristot.tvmaze.series.series.orInvalidId

private const val idEpisodeDetail = "idEpisodeArg"

fun NavGraphBuilder.EpisodeDetailRouter(navController: NavHostController) {
    val route = Screen.EpisodeDetail.route + "/{$idEpisodeDetail}"
    composable(
        route = route,
        arguments = listOf(
            navArgument(idEpisodeDetail) {
                type = NavType.LongType
                nullable = false
            }
        )
    ) { entry ->
        val episodeId = entry.arguments?.getLong(idEpisodeDetail).orInvalidId()
        if (episodeId.isInvalidId()) navController.popBackStack()
        else EpisodeDetailScreen(episodeId)
    }
}
