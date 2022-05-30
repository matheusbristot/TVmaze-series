package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.EpisodeResponse
import com.bristot.tvmaze.series.series.model.Episode

interface EpisodeMapper {
    operator fun invoke(episodeResponse: EpisodeResponse): Episode
    operator fun invoke(episodesResponse: List<EpisodeResponse>): List<Episode>
}
