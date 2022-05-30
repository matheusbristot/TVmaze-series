package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.EpisodeResponse
import com.bristot.tvmaze.series.series.model.Episode
import com.bristot.tvmaze.series.series.orInvalidId


class EpisodeMapperImpl(
    private val imageMapper: ImageMapper
) : EpisodeMapper {

    override fun invoke(episodeResponse: EpisodeResponse): Episode {
        return episode(episodeResponse)
    }

    override fun invoke(episodesResponse: List<EpisodeResponse>): List<Episode> =
        episodesResponse.map { episode(it) }

    private fun episode(episodeResponse: EpisodeResponse): Episode = Episode(
        id = episodeResponse.id.orInvalidId(),
        name = episodeResponse.name.orEmpty(),
        season = episodeResponse.season,
        number = episodeResponse.number,
        airDate = episodeResponse.airDate.orEmpty(),
        airStamp = episodeResponse.airStamp.orEmpty(),
        rating = episodeResponse.rating?.average,
        summary = episodeResponse.summary.orEmpty(),
        image = imageMapper(episodeResponse.image)
    )
}