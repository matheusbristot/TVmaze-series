package com.bristot.tvmaze.series.series

private const val INVALID_ID = -99L

fun Long?.orInvalidId(): Long {
    return this ?: INVALID_ID
}

fun Long.isInvalidId(): Boolean {
    return this == INVALID_ID
}
