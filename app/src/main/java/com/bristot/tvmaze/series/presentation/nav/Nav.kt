package com.bristot.tvmaze.series.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bristot.tvmaze.series.presentation.detail.episode.EpisodeDetailRouter
import com.bristot.tvmaze.series.presentation.detail.show.ShowDetailRouter
import com.bristot.tvmaze.series.presentation.shows.ShowsRouter

@Composable
fun Nav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Shows.route
    ) {
        ShowsRouter(navController)
        ShowDetailRouter(navController)
        EpisodeDetailRouter(navController)
    }
}
