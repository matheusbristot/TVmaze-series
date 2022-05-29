package com.bristot.tvmaze.series.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowResponse(
    @SerialName(value = "id") val id: Long? = null,
    @SerialName(value = "name") val name: String? = null,
    @SerialName(value = "language") val language: String? = null,
    @SerialName(value = "summary") val summary: String? = null,
    @SerialName(value = "image") val image: ImageResponse? = null,
)