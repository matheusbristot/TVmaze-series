package com.bristot.tvmaze.series.presentation.detail.episode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bristot.tvmaze.series.R
import com.bristot.tvmaze.series.presentation.composables.*
import com.bristot.tvmaze.series.presentation.shows.composables.PosterImage
import com.bristot.tvmaze.series.presentation.theme.shapes
import com.bristot.tvmaze.series.series.model.Episode
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EpisodeDetailScreen(
    episodeId: Long,
    viewModel: EpisodeDetailViewModel = koinViewModel { parametersOf(episodeId) }
) {
    val result: EpisodeDetailViewState by viewModel.detail.observeAsState(EpisodeDetailViewState.Init)
    EpisodeDetailsBody(result, viewModel)
}

@Composable
private fun EpisodeDetailsBody(
    result: EpisodeDetailViewState,
    viewModel: EpisodeDetailViewModel
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
                            text = stringResource(id = R.string.episode_detail)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            when (result) {
                EpisodeDetailViewState.Init -> Loading()
                is EpisodeDetailViewState.Error -> {
                    RenderDialog(
                        title = result.title,
                        message = result.message,
                        action = result.action,
                        onConfirmButtonClicked = { viewModel.onEpisodeDetail() },
                        onDismiss = { viewModel.onClear() }
                    )
                }
                is EpisodeDetailViewState.Success -> {
                    AnimatedVisibility(visible = result.loading.not()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Detail(result.episode)
                        }
                    }
                }
                EpisodeDetailViewState.Clear -> {
                    // Nothing to do here
                }
            }
        }
    }
}

@Composable
fun Detail(episode: Episode) = with(episode) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PosterImage(
            imageUrl = image?.original,
            contentDescription = episode.name.orEmpty()
        )
        EpisodeBox(episode)
    }
}

@Composable
fun EpisodeBox(episode: Episode) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(shapes.medium)
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EpisodeName(episode)
        EpisodeNumber(episode)
        EpisodeSeason(episode)
        Rating(episode)
        episode.summary?.let { summary ->
            Summary(summary)
        }
    }
}

@Composable
private fun EpisodeName(episode: Episode) {
    Texth5(
        text = episode.name.orEmpty(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun EpisodeNumber(episode: Episode) {
    val text = if (episode.number != null) {
        stringResource(R.string.episode_number, episode.number)
    } else stringResource(R.string.empty_number)
    EpisodeText(text = text)
}

@Composable
private fun EpisodeSeason(episode: Episode) {
    val text = if (episode.season != null) {
        stringResource(R.string.episode_season, episode.season)
    } else stringResource(R.string.empty_number)
    EpisodeText(text)
}

@Composable
private fun EpisodeText(text: String) {
    TextBody1(text = text, textAlign = TextAlign.Left)
}
