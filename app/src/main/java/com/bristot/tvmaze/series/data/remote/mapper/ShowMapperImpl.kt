package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.series.model.Show
import com.bristot.tvmaze.series.series.orInvalidId


class ShowMapperImpl(private val imageMapper: ImageMapper) : ShowMapper {

    override operator fun invoke(shows: List<ShowResponse>): List<Show> =
        shows.map { show(it) }

    override operator fun invoke(show: ShowResponse): Show = show(show)

    private fun show(showResponse: ShowResponse): Show = Show(
        id = showResponse.id.orInvalidId(),
        name = showResponse.name.orEmpty(),
        language = showResponse.language.orEmpty(),
        genres = showResponse.genres ?: emptyList(),
        summary = showResponse.summary.orEmpty(),
        ended = showResponse.ended.orEmpty(),
        premiered = showResponse.premiered.orEmpty(),
        image = imageMapper(showResponse.image)
    )
}