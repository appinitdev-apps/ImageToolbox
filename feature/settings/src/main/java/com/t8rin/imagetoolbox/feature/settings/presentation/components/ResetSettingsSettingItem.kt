/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.RestartAlt
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.ResetDialog
import com.t8rin.imagetoolbox.core.ui.widget.icon_shape.LocalIconShapeContainerColor
import com.t8rin.imagetoolbox.core.ui.widget.icon_shape.LocalIconShapeContentColor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun ResetSettingsSettingItem(
    onReset: () -> Unit,
    shape: Shape = ShapeDefaults.bottom,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    var showResetDialog by remember { mutableStateOf(false) }

    CompositionLocalProvider(
        LocalIconShapeContentColor provides MaterialTheme.colorScheme.onErrorContainer,
        LocalIconShapeContainerColor provides MaterialTheme.colorScheme.errorContainer
    ) {
        PreferenceItem(
            onClick = {
                showResetDialog = true
            },
            shape = shape,
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme
                .errorContainer
                .copy(alpha = 0.5f),
            title = stringResource(R.string.reset),
            subtitle = stringResource(R.string.reset_settings_sub),
            startIcon = Icons.Rounded.RestartAlt,
            overrideIconShapeContentColor = true
        )
    }

    ResetDialog(
        visible = showResetDialog,
        onDismiss = {
            showResetDialog = false
        },
        onReset = {
            showResetDialog = false
            onReset()
        },
        title = stringResource(R.string.reset),
        text = stringResource(R.string.reset_settings_sub),
        icon = Icons.Rounded.RestartAlt
    )
}