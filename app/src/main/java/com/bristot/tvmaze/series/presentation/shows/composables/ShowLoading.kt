@file:OptIn(ExperimentalFoundationApi::class)

package com.bristot.tvmaze.series.presentation.shows.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.bristot.tvmaze.series.presentation.theme.primaryColor

fun LazyGridScope.renderLoading(loadState: CombinedLoadStates) {
    if (loadState.append is LoadState.Loading) {
        renderAppend()
    } else if (loadState.refresh is LoadState.Loading) {
        renderRefresh()
    }
}

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(2) }

private fun LazyGridScope.renderRefresh() {
    item(span = span) {
        LoadingColumn(modifier = Modifier.fillParentMaxSize())
    }
}

private fun LazyGridScope.renderAppend() {
    item(span = span) {
        val cardHeight = 325.dp
        LoadingColumn(modifier = Modifier.height(cardHeight))
    }
}

@Composable
private fun LoadingColumn(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = primaryColor)
    }
}