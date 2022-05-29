package com.bristot.tvmaze.series.data.remote.paging

import com.bristot.tvmaze.series.data.remote.model.ImageResponse
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.series.model.Image
import com.bristot.tvmaze.series.series.model.Show

class ShowMapperImpl : ShowMapper {

    override operator fun invoke(shows: List<ShowResponse>): List<Show> =
        shows.map { show(it) }

    override operator fun invoke(show: ShowResponse): Show = show(show)

    private fun show(showResponse: ShowResponse): Show = Show(
        id = showResponse.id ?: -99L,
        name = showResponse.name.orEmpty(),
        language = showResponse.language.orEmpty(),
        summary = showResponse.summary.orEmpty(),
        image = image(showResponse.image)
    )

    private fun image(imageResponse: ImageResponse?): Image = Image(
        medium = imageResponse?.medium,
        original = imageResponse?.original
    )
}