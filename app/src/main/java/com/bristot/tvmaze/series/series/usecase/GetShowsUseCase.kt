package com.bristot.tvmaze.series.series.usecase

import androidx.paging.PagingData
import com.bristot.tvmaze.series.series.model.Show
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class GetShowsUseCase(private val repository: ShowRepository) {

    operator fun invoke(): Flow<PagingData<Show>> {
        return repository.getShows()
    }
}