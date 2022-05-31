package com.bristot.tvmaze.series.presentation.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bristot.tvmaze.series.presentation.shows.resource.ShowsResourceProvider
import com.bristot.tvmaze.series.series.model.Show
import com.bristot.tvmaze.series.series.usecase.GetShowsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class ShowsViewModel(
    private val useCase: GetShowsUseCase,
    private val showsResourceProvider: ShowsResourceProvider
) : ViewModel() {

    private val showsError: MutableStateFlow<ShowsErrorState> =
        MutableStateFlow(ShowsErrorState.Clear)

    val stateError = showsError
        .asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(1500),
            initialValue = ShowsErrorState.Clear
        )

    fun onShows(): Flow<PagingData<Show>> = useCase().cachedIn(viewModelScope)

    fun onShowsError() {
        showsError.value = ShowsErrorState.Error(
            title = showsResourceProvider.getErrorTitle(),
            message = showsResourceProvider.getErrorMessage(),
            action = showsResourceProvider.getErrorAction()
        )
    }

    fun onClear() {
        showsError.value = ShowsErrorState.Clear
    }
}