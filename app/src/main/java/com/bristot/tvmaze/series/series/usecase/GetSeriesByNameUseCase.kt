package com.bristot.tvmaze.series.series.usecase

import com.bristot.tvmaze.series.series.model.SearchResult
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSeriesByNameUseCase(private val showRepository: ShowRepository) {

    operator fun invoke(name: String): Flow<SearchResult> {
        return flow {
            try {
                if (name.isEmpty()) {
                    emit(SearchResult.Empty)
                } else {
                    showRepository.getShowsByName(name).collect { results ->
                        if (results.isEmpty()) {
                            emit(SearchResult.NotFound)
                        } else {
                            val showsSortedByScore = results
                                .sortedBy { search -> search.score }
                                .map { show -> show.show }
                            emit(SearchResult.Success(showsSortedByScore))
                        }
                    }
                }
            } catch (e: Exception) {
                emit(SearchResult.Error)
            }
        }
    }
}