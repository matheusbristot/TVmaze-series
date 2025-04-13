package com.bristot.tvmaze.series.presentation.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A flexible card composable that can expand and collapse its content.
 *
 * This composable displays a card with a title and an expand/collapse arrow.
 * When the card is clicked, it will expand or collapse to show/hide the provided content.
 *
 * @param title The title to be displayed on the card.
 * @param defaultElevation The default elevation of the card. Defaults to 12.dp.
 * @param content The composable content to be displayed when the card is expanded.
 *
 * Example Usage:
 *
 * ```
 * FlexibleCard(title = "Card Title") {
 *     Text("This is the expanded content.")
 *     Text("More content can be added here.")
 * }
 * ```
 */
@Composable
fun FlexibleCard(
    title: String,
    defaultElevation: Dp = 12.dp,
    content: @Composable () -> Unit
) {

    var isExpanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetFloatByState(isExpanded), label = String())

    Card(
        modifier = Modifier.fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            isExpanded = !isExpanded
        },
        elevation = CardDefaults.cardElevation(defaultElevation = defaultElevation)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TitleLarge(modifier = Modifier.weight(6f), text = title)
                IconButton(
                    modifier = Modifier.weight(2f).alpha(0.74f).rotate(rotationState),
                    onClick = {
                        isExpanded = !isExpanded
                    }) {
                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow is " +
                                "${if (isExpanded) "expanded" else "not expanded"} " +
                                "click to change state"
                    )
                }
            }
            if (isExpanded) content()
        }
    }
}


private const val EXPANDED_FLOAT = 180f
private const val INITIAL_FLOAT = 0f

private fun targetFloatByState(isExpanded: Boolean): Float =
    if (isExpanded) EXPANDED_FLOAT else INITIAL_FLOAT