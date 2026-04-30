/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.selection

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ZoomIn
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSimpleSettingsInteractor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import kotlinx.coroutines.launch

@Composable
fun MagnifierEnabledSelector(
    modifier: Modifier = Modifier,
    shape: Shape = ShapeDefaults.default,
) {
    val scope = rememberCoroutineScope()
    val settingsState = LocalSettingsState.current
    val settingsInteractor = LocalSimpleSettingsInteractor.current
    PreferenceRowSwitch(
        modifier = modifier,
        shape = shape,
        title = stringResource(R.string.magnifier),
        subtitle = stringResource(R.string.magnifier_sub),
        checked = settingsState.magnifierEnabled,
        onClick = {
            scope.launch {
                settingsInteractor.toggleMagnifierEnabled()
            }
        },
        startIcon = Icons.Outlined.ZoomIn
    )
}