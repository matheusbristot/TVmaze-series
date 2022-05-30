package com.bristot.tvmaze.series.data.remote.datasource.detail

import com.bristot.tvmaze.series.data.remote.model.EpisodeResponse
import retrofit2.Response

interface EpisodeRemoteDataSource {
    suspend fun getAllEpisodesByShowId(showId: Long): Response<List<EpisodeResponse>>
    suspend fun getEpisodeDetails(episodeId: Long): Response<EpisodeResponse>
}