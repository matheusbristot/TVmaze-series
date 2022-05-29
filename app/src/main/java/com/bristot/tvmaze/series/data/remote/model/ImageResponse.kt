package com.bristot.tvmaze.series.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    @SerialName(value = "medium") val medium: String? = null,
    @SerialName(value = "original") val original: String? = null,
)