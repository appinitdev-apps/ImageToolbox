/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.limits_resize.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.MotionPhotosAuto
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch

@Composable
fun AutoRotateLimitBoxToggle(
    value: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PreferenceRowSwitch(
        modifier = modifier,
        title = stringResource(R.string.auto_rotate_limits),
        subtitle = stringResource(R.string.auto_rotate_limits_sub),
        checked = value,
        shape = ShapeDefaults.extraLarge,
        onClick = {
            onClick()
        },
        startIcon = Icons.Rounded.MotionPhotosAuto
    )
}