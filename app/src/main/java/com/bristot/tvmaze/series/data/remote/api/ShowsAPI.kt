package com.bristot.tvmaze.series.data.remote.api

import com.bristot.tvmaze.series.data.remote.model.EpisodeResponse
import com.bristot.tvmaze.series.data.remote.model.SearchShowResponse
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsAPI {

    @GET("shows")
    suspend fun getShows(@Query("page") page: Int): Response<List<ShowResponse>>

    @GET("search/shows")
    suspend fun searchShowsByName(
        @Query(value = "q", encoded = true) name: String
    ): Response<List<SearchShowResponse>>

    @GET("shows/{showId}")
    suspend fun getShowById(
        @Path(value = "showId", encoded = true) showId: Long
    ): Response<ShowResponse>

    @GET("shows/{showId}/episodes")
    suspend fun getAllEpisodesByShowId(
        @Path(value = "showId", encoded = true) showId: Long
    ): Response<List<EpisodeResponse>>

    @GET("episodes/{episodeId}")
    suspend fun getEpisodeDetails(
        @Path(
            value = "episodeId",
            encoded = true
        ) episodeId: Long
    ): Response<EpisodeResponse>
}
