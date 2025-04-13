package com.bristot.tvmaze.series.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.bristot.tvmaze.series.R

/**
 * Displays an error dialog if the `isError` flag is true.
 *
 * This composable function provides a reusable way to display error messages to the user.
 * It uses a boolean flag `isError` to determine if the dialog should be shown. If `isError`
 * is false, the dialog is not rendered. If `isError` is true, it displays a dialog with
 * the specified title, message, and an "Okay" button.
 *
 * @param isError A boolean flag indicating whether an error has occurred and the dialog should be shown.
 *                If `true`, the dialog is displayed. If `false`, the dialog is not displayed.
 * @param title The title of the error dialog.
 * @param message The error message to display in the dialog.
 * @param onConfirmButtonClicked An optional callback function that is invoked when the "Okay" button is clicked.
 *                               If `null`, no action is performed on button click.
 * @param onDismiss An optional callback function that is invoked when the dialog is dismissed (e.g., by clicking outside of it).
 *                  If `null`, no action is performed on dialog dismissal.
 */
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
        title, message, stringResource(R.string.okay), onConfirmButtonClicked, onDismiss
    )
}

/**
 * Displays a customizable dialog with a title, message, and confirm button.
 *
 * This composable function provides a simple way to show an alert dialog to the user.
 * It allows customization of the dialog's title, message, the text of the confirm button,
 * and actions to be performed when the confirm button is clicked or the dialog is dismissed.
 *
 * @param title The title of the dialog, displayed prominently at the top.
 * @param message The main message content of the dialog, explaining the purpose or context.
 * @param action The text to display on the confirm button (e.g., "OK", "Confirm", "Save").
 * @param onConfirmButtonClicked An optional lambda function to be invoked when the confirm button is clicked.
 *                               If null, the confirm button will be displayed but have no action.
 * @param onDismiss An optional lambda function to be invoked when the dialog is dismissed (e.g., by tapping outside or pressing back).
 *                  If null, no action will be performed upon dismissal.
 *
 * Example Usage:
 * ```
 * RenderDialog(
 *     title = "Confirmation",
 *     message = "Are you sure you want to delete this item?",
 *     action = "Delete",
 *     onConfirmButtonClicked = {
 *         // Perform delete action here
 *         println("Item deleted")
 *     },
 *     onDismiss = {
 *          println("Dialog dismissed without deletion")
 *     }
 * )
 * ```
 */
@Composable
fun RenderDialog(
    title: String,
    message: String,
    action: String,
    onConfirmButtonClicked: (() -> Unit)?,
    onDismiss: (() -> Unit)?
) {
    AlertDialog(
        title = { TitleLarge(title) },
        text = { BodyLarge(message) },
        confirmButton = {
            ConfirmButton(action, onConfirmButtonClicked, onDismiss)
        },
        onDismissRequest = { onDismiss?.invoke() },
        properties = DialogProperties(dismissOnClickOutside = false)
    )
}

@Composable
private fun ConfirmButton(
    action: String, onConfirmButtonClicked: (() -> Unit)?,
    onDismiss: (() -> Unit)?,
) {
    Button(modifier = Modifier.padding(8.dp), onClick = {
        onDismiss?.invoke()
        onConfirmButtonClicked?.invoke()
    }, content = { HeadlineSmall(action, textColor = Color.White) })
}