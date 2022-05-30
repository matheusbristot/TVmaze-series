@file:OptIn(ExperimentalSerializationApi::class)

package com.bristot.tvmaze.series.data.di

import androidx.paging.PagingSource
import com.bristot.tvmaze.series.BuildConfig
import com.bristot.tvmaze.series.data.remote.EpisodeRepositoryImpl
import com.bristot.tvmaze.series.data.remote.ShowRepositoryImpl
import com.bristot.tvmaze.series.data.remote.api.ShowsAPI
import com.bristot.tvmaze.series.data.remote.builder.RetrofitBuilderImpl
import com.bristot.tvmaze.series.data.remote.datasource.detail.EpisodeRemoteDataSource
import com.bristot.tvmaze.series.data.remote.datasource.detail.EpisodeRemoteDataSourceImpl
import com.bristot.tvmaze.series.data.remote.datasource.detail.ShowDetailRemoteDataSource
import com.bristot.tvmaze.series.data.remote.datasource.detail.ShowDetailRemoteDataSourceImpl
import com.bristot.tvmaze.series.data.remote.datasource.paging.ShowPagingDataSource
import com.bristot.tvmaze.series.data.remote.datasource.paging.ShowPagingDataSourceImpl
import com.bristot.tvmaze.series.data.remote.datasource.search.SearchShowRemoteDataSource
import com.bristot.tvmaze.series.data.remote.datasource.search.SearchShowRemoteDataSourceImpl
import com.bristot.tvmaze.series.data.remote.mapper.*
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManager
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManagerImpl
import com.bristot.tvmaze.series.data.remote.paging.ShowPagingConfig
import com.bristot.tvmaze.series.data.remote.paging.ShowPagingSource
import com.bristot.tvmaze.series.series.repository.EpisodeRepository
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    foundation()
    show()
    search()
    detail()
    episode()
}


private fun Module.foundation() {
    factory<NetworkManager> { NetworkManagerImpl(androidApplication()) }
    single {
        val timeout = 15L
        RetrofitBuilderImpl(isDebug = BuildConfig.DEBUG, timeout = timeout).getClient()
    }
    factory<ShowsAPI> { get<Retrofit>().create(ShowsAPI::class.java) }
}

private fun Module.episode() {
    factory<EpisodeMapper> { EpisodeMapperImpl(imageMapper = get()) }
    factory<EpisodeRemoteDataSource> { EpisodeRemoteDataSourceImpl(showsAPI = get()) }
    factory<EpisodeRepository> {
        EpisodeRepositoryImpl(
            remoteDataSource = get(),
            episodeMapper = get()
        )
    }
}

private fun Module.detail() {
    factory<ShowDetailRemoteDataSource> { ShowDetailRemoteDataSourceImpl(showsAPI = get()) }
}

private fun Module.search() {
    factory<SearchShowRemoteDataSource> { SearchShowRemoteDataSourceImpl(showsAPI = get()) }
    factory<SearchShowMapper> { SearchShowMapperImpl(showMapper = get()) }
}

private fun Module.show() {
    factory<ImageMapper> { ImageMapperImpl() }
    factory<ShowMapper> { ShowMapperImpl(imageMapper = get()) }
    factory<PagingSource<Int, ShowResponse>> {
        ShowPagingSource(
            api = get(),
            networkManager = get()
        )
    }
    factory { ShowPagingConfig().invoke() }
    factory<ShowPagingDataSource> {
        ShowPagingDataSourceImpl(
            showMapper = get(),
            pagingConfig = get(),
            pagingSourceFactory = { get() }
        )
    }
    factory<ShowRepository> {
        ShowRepositoryImpl(
            showPagingRemoteDataSource = get(),
            searchShowRemoteDataSource = get(),
            detailRemoteDataSource = get(),
            searchShowMapper = get(),
            showMapper = get(),
            networkManager = get()
        )
    }
}