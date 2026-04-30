/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Texture
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun MaskFilterPreference(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    PreferenceItem(
        onClick = onClick,
        startIcon = Icons.Outlined.Texture,
        title = stringResource(R.string.mask_filter),
        subtitle = stringResource(R.string.mask_filter_sub),
        containerColor = color,
        modifier = modifier
    )
}