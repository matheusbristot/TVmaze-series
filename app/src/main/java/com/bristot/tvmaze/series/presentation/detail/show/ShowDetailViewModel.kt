package com.bristot.tvmaze.series.presentation.detail.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bristot.tvmaze.series.series.usecase.GetShowDetailUseCase
import kotlinx.coroutines.launch

class ShowDetailViewModel(
    private val id: Long,
    private val useCase: GetShowDetailUseCase
) : ViewModel() {

    private val state = MutableLiveData<ShowDetailResult>()
    val detail: LiveData<ShowDetailResult> get() = state

    init {
        onDetail()
    }

    fun onDetail() {
        state.value = ShowDetailResult.Init
        viewModelScope.launch {
            try {
                useCase(id).collect { result ->
                    state.postValue(
                        if (result is com.bristot.tvmaze.series.series.model.ShowDetailResult.Success) {
                            ShowDetailResult.Success(result.show, result.seasons)
                        } else {
                            ShowDetailResult.Error("Error", "Try again later")
                        }
                    )
                }
            } catch (e: Exception) {
                state.postValue(ShowDetailResult.Error("Error", "Try again later"))
            }
        }
    }

    fun onClear() {
        state.value = ShowDetailResult.Init
    }
}