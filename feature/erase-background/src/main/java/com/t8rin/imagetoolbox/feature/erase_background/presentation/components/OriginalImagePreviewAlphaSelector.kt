/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ImageSearch
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.AlphaSelector

@Composable
fun OriginalImagePreviewAlphaSelector(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    AlphaSelector(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        title = stringResource(id = R.string.original_image_preview_alpha),
        icon = Icons.Rounded.ImageSearch
    )
}