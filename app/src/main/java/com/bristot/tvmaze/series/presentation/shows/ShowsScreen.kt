@file:OptIn(ExperimentalFoundationApi::class)

package com.bristot.tvmaze.series.presentation.shows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bristot.tvmaze.series.R
import com.bristot.tvmaze.series.presentation.composables.RenderDialog
import com.bristot.tvmaze.series.presentation.search.SearchViewModel
import com.bristot.tvmaze.series.presentation.search.composables.SearchFieldView
import com.bristot.tvmaze.series.presentation.shows.composables.ShowAdapter
import com.bristot.tvmaze.series.presentation.shows.composables.ShowSearchAdapter
import com.bristot.tvmaze.series.presentation.theme.primaryColor
import com.bristot.tvmaze.series.series.model.Show
import org.koin.androidx.compose.getViewModel

@Composable
fun ShowsScreen(
    navController: NavController,
    viewModel: ShowsViewModel = getViewModel(),
    searchViewModel: SearchViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = R.string.app_name))
                        val text by searchViewModel.text.collectAsState()
                        SearchFieldView(text) { showName ->
                            searchViewModel.onTextReceived(showName)
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 12.dp,
            )
        },
        backgroundColor = primaryColor
    ) {
        val lazyPagingItems = viewModel.onShows().collectAsLazyPagingItems()
        val data by searchViewModel.data.collectAsState(emptyList())
        ShowErrorDialog(viewModel, lazyPagingItems)
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (data.isNotEmpty()) {
                ShowSearchAdapter(
                    navController,
                    data
                )
            } else {
                ShowAdapter(
                    navController = navController,
                    tvShows = lazyPagingItems,
                    onPagingError = viewModel::onShowsError
                )
            }
        }
    }
}

@Composable
private fun ShowErrorDialog(
    viewModel: ShowsViewModel,
    lazyPagingItems: LazyPagingItems<Show>
) {
    ErrorDialog(
        state = viewModel.stateError.collectAsState().value,
        onConfirmButtonClicked = { lazyPagingItems.refresh() },
        onDismiss = { viewModel.onClear() }
    )
}

@Composable
private fun ErrorDialog(
    state: ShowsErrorState,
    onConfirmButtonClicked: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
) {
    if (state is ShowsErrorState.Error) {
        RenderDialog(state.title, state.message, state.action, onConfirmButtonClicked, onDismiss)
    }
}
