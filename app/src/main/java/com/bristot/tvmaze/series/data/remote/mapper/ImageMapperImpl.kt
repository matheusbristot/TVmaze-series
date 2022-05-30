package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.ImageResponse
import com.bristot.tvmaze.series.series.model.Image

class ImageMapperImpl : ImageMapper {

    override operator fun invoke(imageResponse: ImageResponse?): Image = Image(
        medium = imageResponse?.medium,
        original = imageResponse?.original
    )
}