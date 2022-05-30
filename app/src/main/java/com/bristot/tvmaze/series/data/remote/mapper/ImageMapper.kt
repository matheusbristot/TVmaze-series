package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.ImageResponse
import com.bristot.tvmaze.series.series.model.Image

interface ImageMapper {
    operator fun invoke(imageResponse: ImageResponse?): Image
}