@file:OptIn(ExperimentalFoundationApi::class)

package com.bristot.tvmaze.series.presentation.shows.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bristot.tvmaze.series.series.model.Show

@Composable
fun ShowAdapter(
    navController: NavController,
    tvShows: LazyPagingItems<Show>,
    onPagingError: () -> Unit
) {
    val cellCount = 2
    val cellState by remember { mutableStateOf(cellCount) }

    LazyVerticalGrid(
        cells = GridCells.Fixed(cellState),
        content = {
            if (tvShows.loadState.refresh is LoadState.Error) {
                onPagingError()
            } else {
                renderShows(tvShows, navController)
                renderLoading(tvShows.loadState)
            }
        }
    )
}

@Composable
fun ShowSearchAdapter(
    navController: NavController,
    shows: List<Show>
) {
    val cellCount = 2
    val cellState by remember { mutableStateOf(cellCount) }

    LazyVerticalGrid(
        cells = GridCells.Fixed(cellState),
        content = {
            renderShows(shows, navController)
        }
    )
}

private fun LazyGridScope.renderShows(
    tvShows: List<Show>,
    navController: NavController
) {
    items(tvShows.size) { index ->
        val show = tvShows[index]
        ShowCard(show = show, navController = navController)
    }
}

private fun LazyGridScope.renderShows(
    tvShows: LazyPagingItems<Show>,
    navController: NavController
) {
    items(tvShows.itemCount) { index ->
        val show = tvShows[index] ?: return@items
        ShowCard(show = show, navController = navController)
    }
}