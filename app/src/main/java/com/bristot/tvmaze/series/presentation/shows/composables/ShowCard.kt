package com.bristot.tvmaze.series.presentation.shows.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bristot.tvmaze.series.presentation.composables.TextBody1
import com.bristot.tvmaze.series.presentation.nav.Screen
import com.bristot.tvmaze.series.presentation.theme.contentForDescriptionColor
import com.bristot.tvmaze.series.series.model.Show

@Composable
fun ShowCard(show: Show, navController: NavController) {
    val cardHeight = 250.dp
    val cardPadding = 2.dp
    val cardElevation = 12.dp
    val modifier = Modifier.fillMaxWidth().height(cardHeight).padding(cardPadding)
    Card(
        modifier = modifier,
        elevation = cardElevation
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .background(Color.White)
                .clickable {
                    navController.navigate(Screen.ShowDetail.route + "/${show.id}")
                },
        ) {
            PosterImage(imageUrl = show.image.medium.orEmpty(), contentDescription = show.name)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(contentForDescriptionColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextBody1(
                    text = show.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp),
                    textColor = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
