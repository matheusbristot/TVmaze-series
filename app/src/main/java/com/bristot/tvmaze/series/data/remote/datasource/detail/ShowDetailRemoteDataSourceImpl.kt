package com.bristot.tvmaze.series.data.remote.datasource.detail

import com.bristot.tvmaze.series.data.remote.api.ShowsAPI
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import retrofit2.Response

class ShowDetailRemoteDataSourceImpl(private val showsAPI: ShowsAPI) : ShowDetailRemoteDataSource {

    override suspend fun getShowById(id: Long): Response<ShowResponse> {
        return showsAPI.getShowById(id)
    }
}
