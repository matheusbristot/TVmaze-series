package com.bristot.tvmaze.series.data.remote.mapper

import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.series.model.Show

interface ShowMapper {
    operator fun invoke(shows: List<ShowResponse>): List<Show>
    operator fun invoke(show: ShowResponse): Show
}
