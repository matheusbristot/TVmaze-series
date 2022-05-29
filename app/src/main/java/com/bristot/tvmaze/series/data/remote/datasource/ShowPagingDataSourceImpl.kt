package com.bristot.tvmaze.series.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.data.remote.paging.ShowMapper
import com.bristot.tvmaze.series.series.model.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ShowPagingDataSourceImpl(
    pagingConfig: PagingConfig,
    private val showRemoteDataSource: PagingSource<Int, ShowResponse>,
    private val showMapper: ShowMapper
) : ShowPagingDataSource {

    private val factoryShowPager = Pager(
        config = pagingConfig,
        pagingSourceFactory = { showRemoteDataSource }
    )

    override fun getShows(): Flow<PagingData<Show>> {
        return factoryShowPager.flow.map { data ->
            data.map { showResponse: ShowResponse -> showMapper(showResponse) }
        }
    }
}
