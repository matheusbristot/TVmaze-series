package com.bristot.tvmaze.series.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
    @SerialName(value = "average") val average: Double?
)
