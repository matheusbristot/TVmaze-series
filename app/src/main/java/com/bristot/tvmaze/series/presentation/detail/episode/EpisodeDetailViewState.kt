package com.bristot.tvmaze.series.presentation.detail.episode

import com.bristot.tvmaze.series.series.model.Episode

sealed interface EpisodeDetailViewState {
    object Init : EpisodeDetailViewState
    object Clear : EpisodeDetailViewState
    data class Success(val episode: Episode, val loading: Boolean = false) : EpisodeDetailViewState
    data class Error(
        val title: String,
        val message: String,
        val action: String,
        val loading: Boolean = false
    ) : EpisodeDetailViewState
}