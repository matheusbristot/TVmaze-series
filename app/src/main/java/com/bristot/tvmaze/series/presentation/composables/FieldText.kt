package com.bristot.tvmaze.series.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.bristot.tvmaze.series.presentation.theme.primaryColor

@Composable
fun TextBody1(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = primaryColor,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Medium,
    maxLines: Int = 2
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun Texth5(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = primaryColor,
    fontSize: TextUnit = MaterialTheme.typography.h5.fontSize,
    maxLines: Int = 2,
    textAlign: TextAlign = TextAlign.Center
) {

    MaterialTheme.typography.h5.fontWeight

    Text(
        text = text,
        textAlign = textAlign,
        color = textColor,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        fontSize = fontSize,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun Texth6(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    maxLines: Int = 2
) {
    Text(
        modifier = modifier,
        text = text,
        color = primaryColor,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h6
    )
}
