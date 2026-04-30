/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Celebration
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ConfettiSettingItem(
    onClick: () -> Unit,
    shape: Shape = ShapeDefaults.top,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val scope = rememberCoroutineScope()
    val settingsState = LocalSettingsState.current
    PreferenceRowSwitch(
        modifier = modifier,
        shape = shape,
        title = stringResource(R.string.confetti),
        subtitle = stringResource(R.string.confetti_sub),
        checked = settingsState.isConfettiEnabled,
        onClick = { isEnabled ->
            onClick()
            if (isEnabled) {
                scope.launch {
                    //Wait for setting to be applied
                    delay(200L)
                    AppToastHost.showConfetti()
                }
            }
        },
        startIcon = Icons.Outlined.Celebration
    )
}