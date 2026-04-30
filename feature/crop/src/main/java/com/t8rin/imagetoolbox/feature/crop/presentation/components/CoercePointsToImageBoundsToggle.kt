/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.crop.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Rectangle
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch

@Composable
fun CoercePointsToImageBoundsToggle(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    PreferenceRowSwitch(
        checked = value,
        onClick = onValueChange,
        title = stringResource(R.string.coerce_points_to_image_bounds),
        subtitle = stringResource(R.string.coerce_points_to_image_bounds_sub),
        startIcon = Icons.Outlined.Rectangle,
        modifier = modifier,
        shape = ShapeDefaults.extraLarge
    )
}