package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.SearchShowResponse
import com.bristot.tvmaze.series.series.model.SearchShow

interface SearchShowMapper {
    operator fun invoke(results: List<SearchShowResponse>): List<SearchShow>
}
