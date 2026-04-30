/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ScissorsSmall
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch


@Composable
fun TrimImageToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    PreferenceRowSwitch(
        modifier = modifier,
        title = stringResource(R.string.trim_image),
        subtitle = stringResource(R.string.trim_image_sub),
        checked = checked,
        containerColor = color,
        shape = ShapeDefaults.extraLarge,
        onClick = onCheckedChange,
        startIcon = Icons.Outlined.ScissorsSmall
    )
}