package com.bristot.tvmaze.series.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bristot.tvmaze.series.R
import com.bristot.tvmaze.series.series.model.Episode

@Composable
fun Rating(episode: Episode) {
    ConstraintLayout {
        val (star, rating) = createRefs()
        Image(
            painterResource(R.drawable.ic_star_rate),
            modifier = Modifier.constrainAs(star) {},
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        TextBody1(
            text = "${episode.rating ?: stringResource(R.string.empty_number)}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(rating) {
                start.linkTo(star.end, margin = 4.dp)
                top.linkTo(star.top)
                bottom.linkTo(star.bottom)
            }
        )
    }
}
