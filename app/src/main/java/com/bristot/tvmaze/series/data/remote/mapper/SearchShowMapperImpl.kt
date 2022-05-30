package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.SearchShowResponse
import com.bristot.tvmaze.series.series.model.SearchShow

class SearchShowMapperImpl(private val showMapper: ShowMapper) : SearchShowMapper {

    override fun invoke(results: List<SearchShowResponse>): List<SearchShow> {
        return results.map { result ->
            SearchShow(
                score = result.score ?: 0.0,
                show = result.showResponse?.let { showMapper(it) }
                    ?: throw IllegalStateException("Show element is nullable")
            )
        }
    }
}