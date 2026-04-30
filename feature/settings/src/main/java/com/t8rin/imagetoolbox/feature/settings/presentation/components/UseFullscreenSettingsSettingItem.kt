/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import com.t8rin.imagetoolbox.core.resources.icons.Fullscreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UseFullscreenSettingsSettingItem(
    onClick: () -> Unit,
    onNavigateToSettings: () -> Unit,
    shape: Shape = ShapeDefaults.center,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current

    PreferenceRowSwitch(
        modifier = modifier,
        shape = shape,
        title = stringResource(R.string.fullscreen_settings),
        subtitle = stringResource(R.string.fullscreen_settings_sub),
        checked = settingsState.useFullscreenSettings,
        onClick = {
            if (it) {
                onNavigateToSettings()
                GlobalScope.launch {
                    delay(1000)
                    onClick()
                }
            } else onClick()
        },
        startIcon = Icons.Rounded.Fullscreen
    )
}