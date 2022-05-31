package com.bristot.tvmaze.series.presentation.di

import com.bristot.tvmaze.series.presentation.detail.episode.EpisodeDetailViewModel
import com.bristot.tvmaze.series.presentation.detail.show.ShowDetailViewModel
import com.bristot.tvmaze.series.presentation.search.SearchViewModel
import com.bristot.tvmaze.series.presentation.shows.ShowsViewModel
import com.bristot.tvmaze.series.presentation.shows.resource.ShowsResourceProvider
import com.bristot.tvmaze.series.presentation.shows.resource.ShowsResourceProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule = module {
    shows()
    viewModel { SearchViewModel(useCase = get()) }
    viewModel { (id: Long) -> ShowDetailViewModel(id = id, useCase = get()) }
    viewModel { (id: Long) -> EpisodeDetailViewModel(id = id, useCase = get()) }
}

private fun Module.shows() {
    factory<ShowsResourceProvider> { ShowsResourceProviderImpl(context = get()) }
    viewModel { ShowsViewModel(useCase = get(), get()) }
}
