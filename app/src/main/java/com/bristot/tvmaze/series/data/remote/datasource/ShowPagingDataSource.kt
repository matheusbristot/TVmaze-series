package com.bristot.tvmaze.series.data.remote.datasource

import androidx.paging.PagingData
import com.bristot.tvmaze.series.series.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowPagingDataSource {
    fun getShows(): Flow<PagingData<Show>>
}
