package com.bristot.tvmaze.series.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchShowResponse(
    @SerialName("score") val score: Double? = null,
    @SerialName("show") val showResponse: ShowResponse? = null
)
