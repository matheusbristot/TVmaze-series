package com.bristot.tvmaze.series.presentation.shows.resource

interface ShowsResourceProvider {
    fun getErrorTitle() : String
    fun getErrorMessage() : String
    fun getErrorAction() : String
}