@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.bristot.tvmaze.series.presentation.search

import androidx.lifecycle.ViewModel
import com.bristot.tvmaze.series.presentation.search.composables.InputWrapper
import com.bristot.tvmaze.series.series.usecase.GetSeriesByNameUseCase
import com.bristot.tvmaze.series.series.model.SearchResult
import com.bristot.tvmaze.series.series.model.Show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class SearchViewModel(
    private val useCase: GetSeriesByNameUseCase
) : ViewModel() {

    val text = MutableStateFlow(InputWrapper())
    val data: Flow<List<Show>> =
        text.debounce(1000L)
            .distinctUntilChanged()
            .flatMapLatest { input ->
                onSearch(input.value)
            }

    fun onTextReceived(text: String) {
        this.text.tryEmit(this.text.value.copy(value = text))
    }

    private fun onSearch(query: String): Flow<List<Show>> {
        return flow {
            try {
                useCase(query).collect { result ->
                    when (result) {
                        SearchResult.Empty,
                        SearchResult.Error,
                        SearchResult.NotFound -> emit(emptyList())
                        is SearchResult.Success -> emit(result.shows)
                    }
                }
            } catch (e: Exception) {
                emit(emptyList())
            }
        }
    }
}