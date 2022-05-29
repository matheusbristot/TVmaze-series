package com.bristot.tvmaze.series.data.remote.paging

import androidx.paging.PagingConfig

private const val PAGE_SIZE = 10

class ShowPagingConfig {

    operator fun invoke() = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = true)
}