package com.bristot.tvmaze.series.series.repository

import com.bristot.tvmaze.series.series.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getAllEpisodesByShowId(showId: Long): Flow<List<Episode>>
    fun getEpisodeDetails(episodeId: Long): Flow<Episode>
}
