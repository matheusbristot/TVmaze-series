package com.bristot.tvmaze.series.presentation.shows

sealed interface ShowsErrorState {

    data class Error(
        val title: String, val message: String, val action: String
    ) : ShowsErrorState

    object Clear : ShowsErrorState
}