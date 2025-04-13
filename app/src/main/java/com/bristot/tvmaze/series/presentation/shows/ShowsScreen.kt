package com.bristot.tvmaze.series.presentation.shows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bristot.tvmaze.series.R
import com.bristot.tvmaze.series.presentation.composables.RenderDialog
import com.bristot.tvmaze.series.presentation.search.SearchViewModel
import com.bristot.tvmaze.series.presentation.search.composables.SearchFieldView
import com.bristot.tvmaze.series.presentation.shows.composables.ShowAdapter
import com.bristot.tvmaze.series.presentation.shows.composables.ShowSearchAdapter
import com.bristot.tvmaze.series.presentation.theme.primaryLight
import com.bristot.tvmaze.series.series.model.Show
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowsScreen(
    navController: NavController,
    viewModel: ShowsViewModel = koinViewModel(),
    searchViewModel: SearchViewModel = koinViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        color = Color.White,
                        text = stringResource(id = R.string.app_name))
                    val text by searchViewModel.text.collectAsState()
                    SearchFieldView(
                        text
                    ) { showName ->
                        searchViewModel.onTextReceived(showName)
                    }
                }
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }, contentColor = primaryLight, content = { paddingValues: PaddingValues ->
        val lazyPagingItems = viewModel.onShows().collectAsLazyPagingItems()
        val data by searchViewModel.data.collectAsState(emptyList())
        ShowErrorDialog(viewModel, lazyPagingItems)
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (data.isNotEmpty()) {
                ShowSearchAdapter(
                    navController, data
                )
            } else {
                ShowAdapter(
                    navController = navController,
                    tvShows = lazyPagingItems,
                    onPagingError = viewModel::onShowsError
                )
            }
        }
    })
}

@Composable
private fun ShowErrorDialog(
    viewModel: ShowsViewModel, lazyPagingItems: LazyPagingItems<Show>
) {
    ErrorDialog(state = viewModel.stateError.collectAsState().value,
        onConfirmButtonClicked = { lazyPagingItems.refresh() },
        onDismiss = { viewModel.onClear() })
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
