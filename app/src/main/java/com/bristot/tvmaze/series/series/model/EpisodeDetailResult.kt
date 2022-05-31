package com.bristot.tvmaze.series.series.model


sealed interface EpisodeDetailResult {
    object Error : EpisodeDetailResult
    data class Success(val episode: Episode) : EpisodeDetailResult
}