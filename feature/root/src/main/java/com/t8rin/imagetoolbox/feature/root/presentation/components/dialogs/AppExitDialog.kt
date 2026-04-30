/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.root.presentation.components.dialogs

import android.os.Build
import androidx.activity.compose.BackHandler
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.DoorBack
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalComponentActivity
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalCurrentScreen
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.feature.root.presentation.screenLogic.RootComponent


@Composable
internal fun AppExitDialog(component: RootComponent) {
    val currentScreen = LocalCurrentScreen.current

    if (currentScreen is Screen.Main) {
        val context = LocalComponentActivity.current

        var showExitDialog by rememberSaveable { mutableStateOf(false) }

        val tiramisu = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        BackHandler(enabled = !tiramisu) {
            if (component.shouldShowDialog) showExitDialog = true
            else context.finishAffinity()
        }

        AppExitDialogImpl(
            onDismiss = { showExitDialog = false },
            visible = showExitDialog && !tiramisu
        )
    }
}

@Composable
private fun AppExitDialogImpl(
    onDismiss: () -> Unit,
    visible: Boolean
) {
    val activity = LocalComponentActivity.current

    EnhancedAlertDialog(
        visible = visible,
        onDismissRequest = onDismiss,
        dismissButton = {
            EnhancedButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = activity::finishAffinity
            ) {
                Text(stringResource(R.string.close))
            }
        },
        confirmButton = {
            EnhancedButton(onClick = onDismiss) {
                Text(stringResource(R.string.stay))
            }
        },
        title = { Text(stringResource(R.string.app_closing)) },
        text = {
            Text(
                stringResource(R.string.app_closing_sub),
                textAlign = TextAlign.Center
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.DoorBack,
                contentDescription = null
            )
        }
    )
}