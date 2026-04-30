/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.resize_group.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.BlurLinear
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch

@Composable
fun UseBlurredBackgroundToggle(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    shape: Shape = ShapeDefaults.default
) {
    PreferenceRowSwitch(
        modifier = modifier,
        title = stringResource(R.string.blur_edges),
        subtitle = stringResource(R.string.blur_edges_sub),
        checked = checked,
        shape = shape,
        containerColor = MaterialTheme.colorScheme.surface,
        onClick = onCheckedChange,
        startIcon = Icons.Outlined.BlurLinear
    )
}