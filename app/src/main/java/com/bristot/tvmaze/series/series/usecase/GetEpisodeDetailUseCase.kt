package com.bristot.tvmaze.series.series.usecase

import com.bristot.tvmaze.series.series.model.Episode
import com.bristot.tvmaze.series.series.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class GetEpisodeDetailUseCase(private val episodeRepository: EpisodeRepository) {

    operator fun invoke(episodeId: Long): Flow<Episode> {
        return episodeRepository.getEpisodeDetails(episodeId)
    }
}