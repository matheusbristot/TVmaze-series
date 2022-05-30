package com.bristot.tvmaze.series.data.remote.datasource.detail

import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import retrofit2.Response

interface ShowDetailRemoteDataSource {
    suspend fun getShowById(id: Long): Response<ShowResponse>
}