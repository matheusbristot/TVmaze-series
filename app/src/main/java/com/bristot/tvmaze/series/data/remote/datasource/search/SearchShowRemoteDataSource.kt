package com.bristot.tvmaze.series.data.remote.datasource.search

import com.bristot.tvmaze.series.data.remote.model.SearchShowResponse
import retrofit2.Response

interface SearchShowRemoteDataSource {
    suspend fun getShowByName(showName: String): Response<List<SearchShowResponse>>
}
