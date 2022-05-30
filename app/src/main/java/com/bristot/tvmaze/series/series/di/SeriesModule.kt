package com.bristot.tvmaze.series.series.di

import com.bristot.tvmaze.series.series.usecase.GetEpisodeDetailUseCase
import com.bristot.tvmaze.series.series.usecase.GetShowDetailUseCase
import com.bristot.tvmaze.series.series.usecase.GetSeriesByNameUseCase
import com.bristot.tvmaze.series.series.usecase.GetShowsUseCase
import org.koin.dsl.module

val seriesModule = module {
    factory { GetShowsUseCase(repository = get()) }
    factory { GetSeriesByNameUseCase(showRepository = get()) }
    factory { GetShowDetailUseCase(showRepository = get(), episodeRepository = get()) }
    factory { GetEpisodeDetailUseCase(episodeRepository = get()) }
}
