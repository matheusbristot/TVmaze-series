package com.bristot.tvmaze.series.presentation.composables

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.bristot.tvmaze.series.R

@Composable
fun Summary(summary: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Texth5(
            modifier = modifier,
            text = stringResource(R.string.summary),
            textAlign = TextAlign.Start
        )
        /* https://stackoverflow.com/a/68397058 */
        AndroidView(
            modifier = modifier,
            factory = { context ->
                TextView(context).also { textView ->
                    textView.setTextColor(ContextCompat.getColor(context, R.color.primary_color))
                }
            },
            update = { it.text = HtmlCompat.fromHtml(summary, HtmlCompat.FROM_HTML_MODE_COMPACT) }
        )
    }
}
