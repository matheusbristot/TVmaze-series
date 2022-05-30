package com.bristot.tvmaze.series.series.usecase

import com.bristot.tvmaze.series.series.model.Episode
import com.bristot.tvmaze.series.series.model.Season
import com.bristot.tvmaze.series.series.model.ShowDetailResult
import com.bristot.tvmaze.series.series.repository.EpisodeRepository
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip

class GetShowDetailUseCase(
    private val showRepository: ShowRepository,
    private val episodeRepository: EpisodeRepository
) {

    operator fun invoke(id: Long): Flow<ShowDetailResult> {
        return flow {
            try {
                showRepository.getShowById(id).zip(
                    episodeRepository.getAllEpisodesByShowId(id)
                ) { show, episodes ->
                    ShowDetailResult.Success(show, seasons(episodes))
                }.collect { success -> emit(success) }
            } catch (e: Exception) {
                emit(ShowDetailResult.Error)
            }
        }
    }

    private fun seasons(episodes: List<Episode>): List<Season> = episodes
        .groupBy { episode -> episode.season }
        .map { mapEntry -> Season(mapEntry.key ?: 0, mapEntry.value) }
        .sortedByDescending { it.number }
}