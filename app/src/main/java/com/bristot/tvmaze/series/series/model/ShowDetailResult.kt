package com.bristot.tvmaze.series.series.model

sealed interface ShowDetailResult {
    data class Success(val show: Show, val seasons: List<Season>) : ShowDetailResult
    object Error : ShowDetailResult
}