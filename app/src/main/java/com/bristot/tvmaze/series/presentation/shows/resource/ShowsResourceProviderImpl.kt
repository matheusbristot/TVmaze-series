package com.bristot.tvmaze.series.presentation.shows.resource

import android.content.Context
import com.bristot.tvmaze.series.R

class ShowsResourceProviderImpl(
    private val context: Context
) : ShowsResourceProvider {

    override fun getErrorTitle(): String = context.getString(R.string.shows_fetch_error_title)

    override fun getErrorMessage(): String = context.getString(R.string.shows_fetch_error_message)

    override fun getErrorAction(): String = context.getString(R.string.try_again)
}