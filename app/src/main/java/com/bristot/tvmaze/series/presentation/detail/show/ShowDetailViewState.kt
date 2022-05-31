package com.bristot.tvmaze.series.presentation.detail.show

import com.bristot.tvmaze.series.series.model.Season
import com.bristot.tvmaze.series.series.model.Show

sealed interface ShowDetailViewState {
    object Init : ShowDetailViewState
    data class Success(
        val show: Show,
        val seasons: List<Season>,
        val loading: Boolean = false
    ) : ShowDetailViewState

    data class Error(
        val title: String,
        val message: String,
        val loading: Boolean = false
    ) : ShowDetailViewState
}