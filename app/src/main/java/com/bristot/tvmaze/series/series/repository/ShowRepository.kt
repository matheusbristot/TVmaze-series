package com.bristot.tvmaze.series.series.repository

import androidx.paging.PagingData
import com.bristot.tvmaze.series.series.model.SearchShow
import com.bristot.tvmaze.series.series.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    fun getShows(): Flow<PagingData<Show>>
    fun getShowsByName(name: String): Flow<List<SearchShow>>
    fun getShowById(id: Long): Flow<Show>
}