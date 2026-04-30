/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.LinearScale
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch

@Composable
fun ScaleSmallImagesToLargeToggle(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    PreferenceRowSwitch(
        modifier = modifier,
        title = stringResource(R.string.scale_small_images_to_large),
        subtitle = stringResource(R.string.scale_small_images_to_large_sub),
        checked = checked,
        containerColor = Color.Unspecified,
        shape = ShapeDefaults.extraLarge,
        onClick = onCheckedChange,
        startIcon = Icons.Rounded.LinearScale
    )
}