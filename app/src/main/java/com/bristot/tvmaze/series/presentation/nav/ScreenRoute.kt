package com.bristot.tvmaze.series.presentation.nav

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object ShowDetail : Screen("show_detail")
    object EpisodeDetail : Screen("episode_detail")
}