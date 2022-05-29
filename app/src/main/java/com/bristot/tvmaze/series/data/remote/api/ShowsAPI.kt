package com.bristot.tvmaze.series.data.remote.api

import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsAPI {

    @GET("shows")
    suspend fun getShows(@Query("page") page: Int): Response<List<ShowResponse>>
}