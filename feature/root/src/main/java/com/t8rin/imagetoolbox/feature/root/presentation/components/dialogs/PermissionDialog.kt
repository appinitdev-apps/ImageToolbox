/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.root.presentation.components.dialogs

import android.Manifest
import android.os.Build
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Storage
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.needToShowStoragePermissionRequest
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.requestStoragePermission
import com.t8rin.imagetoolbox.core.ui.utils.permission.PermissionUtils.hasPermissionAllowed
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalComponentActivity
import com.t8rin.imagetoolbox.core.ui.utils.provider.rememberCurrentLifecycleEvent
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import kotlinx.coroutines.delay

@Composable
internal fun PermissionDialog() {
    val context = LocalComponentActivity.current
    val settingsState = LocalSettingsState.current

    var showDialog by remember { mutableStateOf(false) }

    val currentLifecycleEvent = rememberCurrentLifecycleEvent()
    LaunchedEffect(
        showDialog,
        context,
        settingsState,
        currentLifecycleEvent
    ) {
        showDialog = context.needToShowStoragePermissionRequest() == true
        while (showDialog) {
            showDialog = context.needToShowStoragePermissionRequest() == true
            delay(100)
        }
    }

    var requestedOnce by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && !requestedOnce) {
            val notAllowed = listOf(
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.POST_NOTIFICATIONS
            ).filter { !context.hasPermissionAllowed(it) }

            if (notAllowed.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    context,
                    buildList {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            add(Manifest.permission.READ_MEDIA_IMAGES)
                        }
                        addAll(notAllowed)
                    }.toTypedArray(),
                    0
                )
            }
            requestedOnce = true
        }
    }

    EnhancedAlertDialog(
        visible = showDialog,
        onDismissRequest = { },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Storage,
                contentDescription = null
            )
        },
        title = { Text(stringResource(R.string.permission)) },
        text = {
            Text(stringResource(R.string.permission_sub))
        },
        confirmButton = {
            EnhancedButton(
                onClick = {
                    context.requestStoragePermission()
                }
            ) {
                Text(stringResource(id = R.string.grant))
            }
        }
    )
}