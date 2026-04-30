/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AutoFixHigh
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun BasicFilterPreference(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    PreferenceItem(
        onClick = onClick,
        startIcon = Icons.Rounded.AutoFixHigh,
        title = stringResource(R.string.filter),
        subtitle = stringResource(R.string.filter_sub),
        containerColor = color,
        modifier = modifier
    )
}