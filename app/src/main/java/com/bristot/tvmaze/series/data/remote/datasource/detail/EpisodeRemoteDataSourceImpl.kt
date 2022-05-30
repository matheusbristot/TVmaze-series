package com.bristot.tvmaze.series.data.remote.datasource.detail

import com.bristot.tvmaze.series.data.remote.api.ShowsAPI
import com.bristot.tvmaze.series.data.remote.model.EpisodeResponse
import retrofit2.Response

class EpisodeRemoteDataSourceImpl(private val showsAPI: ShowsAPI) : EpisodeRemoteDataSource {

    override suspend fun getAllEpisodesByShowId(showId: Long): Response<List<EpisodeResponse>> {
        return showsAPI.getAllEpisodesByShowId(showId)
    }

    override suspend fun getEpisodeDetails(episodeId: Long): Response<EpisodeResponse> {
        return showsAPI.getEpisodeDetails(episodeId)
    }
}