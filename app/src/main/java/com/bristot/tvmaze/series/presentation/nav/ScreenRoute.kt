package com.bristot.tvmaze.series.presentation.nav

sealed class Screen(val route: String) {
    object Shows : Screen("shows")
    object ShowDetail : Screen("show_detail")
    object EpisodeDetail : Screen("episode_detail")
}