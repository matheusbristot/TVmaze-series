package com.bristot.tvmaze.series.presentation.search.composables

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(val value: String = String()) : Parcelable
