package com.bristot.tvmaze.series.series.model

data class Episode(
    val id: Long,
    val name: String? = null,
    val season: Int? = null,
    val number: Int? = null,
    val airDate: String? = null,
    val airStamp: String? = null,
    val rating: Double? = null,
    val summary: String? = null,
    val image: Image? = null,
)
