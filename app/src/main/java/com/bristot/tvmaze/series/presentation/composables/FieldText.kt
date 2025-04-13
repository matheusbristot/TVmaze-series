package com.bristot.tvmaze.series.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.bristot.tvmaze.series.presentation.theme.primaryLight

/**
 * A composable function that displays text with the Material Design "bodyLarge" style.
 *
 * This function simplifies the creation of text elements with the predefined
 * `bodyLarge` typography from MaterialTheme, allowing customization of text, color,
 * alignment, weight and the maximum number of lines.
 *
 * @param text The text content to be displayed.
 * @param modifier Modifiers to apply to the text element.
 * @param textColor The color of the text. Defaults to `primaryLight`.
 * @param textAlign The horizontal alignment of the text. Defaults to `TextAlign.Center`.
 * @param fontWeight The weight of the text (e.g., bold, normal). Defaults to `FontWeight.Medium`.
 * @param maxLines The maximum number of lines the text can occupy. Defaults to 2.
 *                 If the text exceeds this limit, it will be truncated with an ellipsis.
 */
@Composable
fun BodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = primaryLight,
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
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge
    )
}

/**
 * A composable function that displays text with the Material Design headlineSmall style.
 *
 * This function uses the `headlineSmall` typography style from the current `MaterialTheme`.
 * It provides sensible defaults for common use cases, including text alignment, color, and
 * the maximum number of lines to display.
 *
 * @param text The text to display.
 * @param modifier Modifier for styling and layout adjustments.
 * @param textColor The color of the text. Defaults to `primaryLight`.
 * @param maxLines The maximum number of lines for the text to span. If the text exceeds
 *   this limit, it will be truncated with an ellipsis. Defaults to 2.
 * @param textAlign The horizontal alignment of the text within its container. Defaults
 *   to `TextAlign.Center`.
 */
@Composable
fun HeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = primaryLight,
    maxLines: Int = 2,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        textAlign = textAlign,
        color = textColor,
        modifier = modifier,
        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
        maxLines = maxLines,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.headlineSmall
    )
}

/**
 * A large title composable that displays text with a bold, large font.
 *
 * This composable is a convenience wrapper around the [Text] composable, preconfigured
 * with styling suitable for a large title. It uses the `titleLarge` style from
 * [MaterialTheme.typography], sets the text color to [primaryLight], and defaults to
 * bold font weight.
 *
 * @param text The text to display in the title.
 * @param modifier Modifier to apply to the Text composable.
 * @param maxLines The maximum number of lines for the text to span. If the text exceeds
 *   this number of lines, it will be truncated with an ellipsis. Defaults to 2.
 */
@Composable
fun TitleLarge(
    text: String, modifier: Modifier = Modifier, maxLines: Int = 2
) {
    Text(
        modifier = modifier,
        text = text,
        color = primaryLight,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge
    )
}
