package com.bristot.tvmaze.series.presentation.detail.show

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bristot.tvmaze.series.R
import com.bristot.tvmaze.series.presentation.composables.ErrorDialog
import com.bristot.tvmaze.series.presentation.composables.FlexibleCard
import com.bristot.tvmaze.series.presentation.composables.Loading
import com.bristot.tvmaze.series.presentation.composables.Rating
import com.bristot.tvmaze.series.presentation.composables.Summary
import com.bristot.tvmaze.series.presentation.composables.TextBody1
import com.bristot.tvmaze.series.presentation.composables.Texth5
import com.bristot.tvmaze.series.presentation.nav.Screen
import com.bristot.tvmaze.series.presentation.shows.composables.PosterImageRounded
import com.bristot.tvmaze.series.presentation.theme.primaryColor
import com.bristot.tvmaze.series.presentation.theme.shapes
import com.bristot.tvmaze.series.series.model.Episode
import com.bristot.tvmaze.series.series.model.Season
import com.bristot.tvmaze.series.series.model.Show
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TVShowDetailsScreen(
    navController: NavHostController,
    showId: Long,
    viewModel: ShowDetailViewModel = koinViewModel { parametersOf(showId) }
) {
    val state: ShowDetailViewState by viewModel.detail.observeAsState(ShowDetailViewState.Init)
    ShowDetailsBody(viewModel, state, navController)
}

@Composable
private fun ShowDetailsBody(
    viewModel: ShowDetailViewModel,
    result: ShowDetailViewState,
    navController: NavController
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
                        Text(
                            text = stringResource(id = R.string.detail)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        content = { paddingValues: PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                when (result) {
                    is ShowDetailViewState.Error -> {
                        ErrorDialog(
                            true,
                            title = result.title,
                            message = result.message,
                            onConfirmButtonClicked = {
                                viewModel.onDetail()
                            },
                            onDismiss = {
                                viewModel.onDetail()
                            }
                        )
                    }

                    ShowDetailViewState.Init -> Loading()
                    is ShowDetailViewState.Success -> {
                        AnimatedVisibility(visible = result.loading.not()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                ShowDetails(result.show)
                                ShowSeasons(result.seasons, navController)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ShowDetails(show: Show) = with(show) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowName(show)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            PosterImageRounded(
                imageUrl = image.medium,
                contentDescription = show.name
            )
            Column(modifier = Modifier.padding(8.dp)) {
                ShowElement(prefix = R.string.premiered, fieldValue = show.premiered)
                ShowElement(prefix = R.string.ended, fieldValue = show.ended)
                ShowGenres(show.genres)
            }
        }
        Summary(
            summary = show.summary,
            modifier = Modifier
                .clip(shapes.medium)
                .background(Color.White)
                .padding(vertical = 8.dp, horizontal = 8.dp),
        )
    }
}

@Composable
fun ShowGenres(genres: List<String>) {
    if (genres.isNotEmpty()) {
        Row {
            TextBody1(
                stringResource(id = R.string.genres),
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.width(2.dp))
            genres.forEachIndexed { index, text ->
                TextBody1(
                    text = if (index < genres.size - 1) {
                        "$text, "
                    } else {
                        text
                    }
                )
            }
        }
    }
}

@Composable
private fun ShowName(show: Show) {
    Spacer(Modifier.size(8.dp))
    Texth5(
        text = show.name,
        textAlign = TextAlign.Center
    )
    Spacer(Modifier.size(8.dp))
}

@Composable
fun ShowElement(@StringRes prefix: Int, fieldValue: String?) {
    val fieldName = stringResource(prefix)
    Row(horizontalArrangement = Arrangement.Center) {
        TextBody1(text = "$fieldName:", fontWeight = FontWeight.ExtraBold, maxLines = 2)
        Spacer(modifier = Modifier.width(2.dp))
        val text = if (fieldValue.isNullOrEmpty()) {
            stringResource(id = R.string.no_name)
        } else {
            fieldValue
        }
        TextBody1(text = text)
    }
}

@Composable
fun ShowSeasons(seasons: List<Season>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        Texth5(
            text = stringResource(id = R.string.seasons) + ": ${seasons.size}",
            textColor = primaryColor
        )
        seasons.forEach { season ->
            Spacer(modifier = Modifier.height(4.dp))
            val title = stringResource(R.string.season) + " #${season.number}"
            FlexibleCard(title) {
                Episodes(season.episodes, navController)
            }
        }
    }
}

@Composable
fun Episodes(episodes: List<Episode>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shapes.small)
            .background(Color.White)
    ) {
        episodes.forEach {
            Episode(it, navController)
        }
    }
}

@Composable
fun Episode(episode: Episode, navController: NavController) {
    BoxWithConstraints {
        Card(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.EpisodeDetail.route + "/${episode.id}")
                },
            elevation = 8.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                TextBody1(
                    text = "#${episode.number} - ${episode.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontWeight = FontWeight.Bold,
                )
                TextBody1(
                    text = stringResource(R.string.date) + " ${episode.airDate}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontWeight = FontWeight.Bold,
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Rating(episode)
                }
            }
        }
    }
}
