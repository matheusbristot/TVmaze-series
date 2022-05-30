package com.bristot.tvmaze.series.data.remote.datasource.paging

import androidx.paging.*
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.data.remote.mapper.ShowMapper
import com.bristot.tvmaze.series.series.model.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ShowPagingDataSourceImpl(
    pagingConfig: PagingConfig,
    private val showMapper: ShowMapper,
    pagingSourceFactory: () -> PagingSource<Int, ShowResponse>
) : ShowPagingDataSource {

    private val factoryShowPager = Pager(
        config = pagingConfig,
        pagingSourceFactory = pagingSourceFactory
    )

    override fun getShows(): Flow<PagingData<Show>> {
        return factoryShowPager.flow.map { data ->
            data.map { showResponse: ShowResponse -> showMapper(showResponse) }
        }
    }
}
