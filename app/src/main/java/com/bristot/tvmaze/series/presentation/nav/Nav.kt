package com.bristot.tvmaze.series.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bristot.tvmaze.series.presentation.detail.episode.EpisodeDetailRouter
import com.bristot.tvmaze.series.presentation.detail.show.ShowDetailRouter
import com.bristot.tvmaze.series.presentation.show.MainRouter

@Composable
fun Nav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        MainRouter(navController)
        ShowDetailRouter(navController)
        EpisodeDetailRouter(navController)
    }
}


