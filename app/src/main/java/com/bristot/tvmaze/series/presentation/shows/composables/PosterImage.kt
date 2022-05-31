package com.bristot.tvmaze.series.presentation.shows.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.SubcomposeAsyncImageScope
import com.bristot.tvmaze.series.R

@Composable
fun PosterImage(imageUrl: String?, contentDescription: String) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 260.dp),
        contentDescription = stringResource(R.string.poster_description)
    ) { UnwrapState(scope = this, contentDescription = contentDescription) }
}

@Composable
fun PosterImageRounded(imageUrl: String?, contentDescription: String) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.clip(RoundedCornerShape(128.dp)),
        contentDescription = stringResource(R.string.poster_description)
    ) { UnwrapState(scope = this, contentDescription = contentDescription) }
}

@Composable
private fun UnwrapState(scope: SubcomposeAsyncImageScope, contentDescription: String) {
    when (scope.painter.state) {
        is AsyncImagePainter.State.Loading -> RenderLoading()
        is AsyncImagePainter.State.Error,
        is AsyncImagePainter.State.Empty -> RenderDefault(contentDescription)
        else -> scope.SubcomposeAsyncImageContent()
    }
}

@Composable
private fun RenderDefault(contentDescription: String) {
    Image(
        painterResource(R.drawable.ic_no_poster),
        contentDescription = "No image available for $contentDescription",
        contentScale = ContentScale.Fit,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun RenderLoading() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val progressBarSize = 50.dp
        CircularProgressIndicator(Modifier.size(progressBarSize))
    }
}