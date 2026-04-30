/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.dialogs

import androidx.activity.compose.BackHandler
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Save
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton

@Composable
fun ExitWithoutSavingDialog(
    onExit: () -> Unit,
    onDismiss: () -> Unit,
    visible: Boolean,
    placeAboveAll: Boolean = false,
    text: String = stringResource(R.string.image_not_saved_sub),
    title: String = stringResource(R.string.image_not_saved),
    icon: ImageVector = Icons.Outlined.Save
) {
    val settingsState = LocalSettingsState.current

    if (!settingsState.enableToolExitConfirmation) {
        LaunchedEffect(visible) {
            if (visible) onExit()
        }
    } else {
        EnhancedAlertDialog(
            visible = visible,
            onDismissRequest = onDismiss,
            placeAboveAll = placeAboveAll,
            dismissButton = {
                EnhancedButton(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    onClick = {
                        onDismiss()
                        onExit()
                    }
                ) {
                    Text(stringResource(R.string.exit))
                }
            },
            confirmButton = {
                EnhancedButton(
                    onClick = onDismiss
                ) {
                    Text(stringResource(R.string.stay))
                }
            },
            title = { Text(text = title) },
            text = {
                Text(
                    text = text,
                    textAlign = TextAlign.Center
                )
            },
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
fun ExitBackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val settingsState = LocalSettingsState.current

    if (settingsState.enableToolExitConfirmation) {
        BackHandler(
            enabled = enabled,
            onBack = onBack
        )
    }
}