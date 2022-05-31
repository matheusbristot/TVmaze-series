package com.bristot.tvmaze.series.presentation.detail.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bristot.tvmaze.series.series.model.EpisodeDetailResult
import com.bristot.tvmaze.series.series.usecase.GetEpisodeDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(
    private val id: Long,
    private val useCase: GetEpisodeDetailUseCase
) : ViewModel() {

    private val detailMutableState = MutableLiveData<EpisodeDetailViewState>()
    val detail: LiveData<EpisodeDetailViewState> get() = detailMutableState

    init {
        onEpisodeDetail()
    }

    fun onEpisodeDetail() {
        detailMutableState.value = EpisodeDetailViewState.Init
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCase(this@EpisodeDetailViewModel.id).collect { result ->
                    if (result == EpisodeDetailResult.Error) {
                        detailMutableState.postValue(onError())
                    } else if (result is EpisodeDetailResult.Success) {
                        detailMutableState.postValue(EpisodeDetailViewState.Success(result.episode))
                    }
                }
            } catch (e: Exception) {
                detailMutableState.postValue(onError())
            }
        }
    }

    fun onClear() {
        detailMutableState.value = EpisodeDetailViewState.Clear
    }

    private fun onError() = EpisodeDetailViewState.Error(
        "Error",
        "Try again later",
        "Try Again"
    )
}
