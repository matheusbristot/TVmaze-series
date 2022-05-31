package com.bristot.tvmaze.series

import android.app.Application
import com.bristot.tvmaze.series.data.di.dataModule
import com.bristot.tvmaze.series.presentation.di.presentationModule
import com.bristot.tvmaze.series.series.di.seriesModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalSerializationApi
class TVmazeSeriesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TVmazeSeriesApplication)
            modules(dataModule, seriesModule, presentationModule)
        }
    }
}