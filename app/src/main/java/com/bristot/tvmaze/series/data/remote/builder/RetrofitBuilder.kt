package com.bristot.tvmaze.series.data.remote.builder

import retrofit2.Retrofit

interface RetrofitBuilder {
    fun getClient(): Retrofit
}
