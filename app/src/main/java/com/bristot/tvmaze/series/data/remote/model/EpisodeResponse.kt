package com.bristot.tvmaze.series.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    @SerialName(value = "id") val id: Long? = null,
    @SerialName(value = "name") val name: String? = null,
    @SerialName(value = "season") val season: Int? = null,
    @SerialName(value = "number") val number: Int? = null,
    @SerialName(value = "airdate") val airDate: String? = null,
    @SerialName(value = "airstamp") val airStamp: String? = null,
    @SerialName(value = "rating") val rating: RatingResponse? = null,
    @SerialName(value = "summary") val summary: String? = null,
    @SerialName(value = "image") val image: ImageResponse? = null,
)
