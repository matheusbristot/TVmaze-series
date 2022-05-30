package com.bristot.tvmaze.series.data.remote.datasource.search

import com.bristot.tvmaze.series.data.remote.api.ShowsAPI
import com.bristot.tvmaze.series.data.remote.model.SearchShowResponse
import retrofit2.Response

class SearchShowRemoteDataSourceImpl(
    private val showsAPI: ShowsAPI
) : SearchShowRemoteDataSource {

    override suspend fun getShowByName(showName: String): Response<List<SearchShowResponse>> {
        return showsAPI.searchShowsByName(name = showName)
    }
}