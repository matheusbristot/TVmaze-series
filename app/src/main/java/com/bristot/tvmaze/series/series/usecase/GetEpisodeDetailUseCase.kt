package com.bristot.tvmaze.series.series.usecase

import com.bristot.tvmaze.series.series.model.EpisodeDetailResult
import com.bristot.tvmaze.series.series.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetEpisodeDetailUseCase(private val episodeRepository: EpisodeRepository) {

    operator fun invoke(episodeId: Long): Flow<EpisodeDetailResult> {
        return flow {
            try {
                episodeRepository.getEpisodeDetails(episodeId)
                    .catch { emit(EpisodeDetailResult.Error) }
                    .collect { episode ->
                        emit(EpisodeDetailResult.Success(episode))
                    }
            } catch (e: Exception) {
                emit(EpisodeDetailResult.Error)
            }
        }
    }
}