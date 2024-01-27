package com.bristot.tvmaze.series.presentation.nav

sealed class Screen(val route: String) {
    data object Shows : Screen("shows")
    data object ShowDetail : Screen("show_detail")
    data object EpisodeDetail : Screen("episode_detail")
}