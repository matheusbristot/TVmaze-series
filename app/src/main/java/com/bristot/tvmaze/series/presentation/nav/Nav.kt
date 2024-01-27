package com.bristot.tvmaze.series.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bristot.tvmaze.series.presentation.detail.episode.episodeDetailRouter
import com.bristot.tvmaze.series.presentation.detail.show.showDetailRouter
import com.bristot.tvmaze.series.presentation.shows.showsRouter

@Composable
fun Nav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Shows.route
    ) {
        showsRouter(navController)
        showDetailRouter(navController)
        episodeDetailRouter(navController)
    }
}
