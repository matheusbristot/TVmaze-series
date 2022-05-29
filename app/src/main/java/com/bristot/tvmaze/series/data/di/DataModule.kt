@file:OptIn(ExperimentalSerializationApi::class)

package com.bristot.tvmaze.series.data.di

import androidx.paging.PagingSource
import com.bristot.tvmaze.series.BuildConfig
import com.bristot.tvmaze.series.data.remote.ShowRepositoryImpl
import com.bristot.tvmaze.series.data.remote.api.ShowsAPI
import com.bristot.tvmaze.series.data.remote.builder.RetrofitBuilderImpl
import com.bristot.tvmaze.series.data.remote.datasource.ShowPagingDataSource
import com.bristot.tvmaze.series.data.remote.datasource.ShowPagingDataSourceImpl
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManager
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManagerImpl
import com.bristot.tvmaze.series.data.remote.paging.ShowMapper
import com.bristot.tvmaze.series.data.remote.paging.ShowMapperImpl
import com.bristot.tvmaze.series.data.remote.paging.ShowPagingConfig
import com.bristot.tvmaze.series.data.remote.paging.ShowPagingSource
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    foundation()
    show()
}


private fun Module.foundation() {
    factory<NetworkManager> { NetworkManagerImpl(androidApplication()) }
    single {
        val timeout = 15L
        RetrofitBuilderImpl(isDebug = BuildConfig.DEBUG, timeout = timeout).getClient()
    }
    factory<ShowsAPI> { get<Retrofit>().create(ShowsAPI::class.java) }
}

private fun Module.show() {
    factory<ShowMapper> { ShowMapperImpl() }
    factory<PagingSource<Int, ShowResponse>> { ShowPagingSource(api = get()) }
    factory { ShowPagingConfig().invoke() }
    factory<ShowPagingDataSource> {
        ShowPagingDataSourceImpl(
            showRemoteDataSource = get(),
            showMapper = get(),
            pagingConfig = get()
        )
    }
    factory<ShowRepository> { ShowRepositoryImpl(showPagingRemoteDataSource = get()) }
}