package com.bristot.tvmaze.series.series.model

sealed interface SearchResult {
    object Empty : SearchResult
    object NotFound : SearchResult
    data class Success(val shows: List<Show>) : SearchResult
    object Error : SearchResult
}