package com.bristot.tvmaze.series.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bristot.tvmaze.series.R

@Composable
fun ErrorDialog(
    isError: Boolean,
    title: String,
    message: String,
    onConfirmButtonClicked: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
) {
    if (!isError) return
    RenderDialog(
        title, message,
        stringResource(R.string.okay),
        onConfirmButtonClicked,
        onDismiss
    )
}

@Composable
fun RenderDialog(
    title: String, message: String, action: String,
    onConfirmButtonClicked: (() -> Unit)?,
    onDismiss: (() -> Unit)?
) {
    AlertDialog(
        title = { Title(title) },
        text = { Text(message) },
        confirmButton = {
            ConfirmButton(action, onConfirmButtonClicked)
        },
        onDismissRequest = { onDismiss?.invoke() },
    )
}


@Composable
private fun Title(title: String) {
    Text(text = title, fontSize = 18.sp)
}

@Composable
private fun ConfirmButton(
    action: String,
    onConfirmButtonClicked: (() -> Unit)?
) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = {
            onConfirmButtonClicked?.invoke()
        }) { Text(action) }
}