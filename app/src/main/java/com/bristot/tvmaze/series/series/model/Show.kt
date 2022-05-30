package com.bristot.tvmaze.series.series.model

data class Show(
    val id: Long,
    val name: String,
    val language: String,
    val summary: String,
    val genres: List<String>,
    val ended: String,
    val premiered: String,
    val image: Image
)
