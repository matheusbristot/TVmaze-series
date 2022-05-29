package com.bristot.tvmaze.series.data.remote

import androidx.paging.PagingData
import com.bristot.tvmaze.series.data.remote.datasource.ShowPagingDataSource
import com.bristot.tvmaze.series.series.model.Show
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class ShowRepositoryImpl(
    private val showPagingRemoteDataSource: ShowPagingDataSource
) : ShowRepository {

    override fun getShows(): Flow<PagingData<Show>> = showPagingRemoteDataSource.getShows()
}
