/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.crop.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.domain.utils.roundTo
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ScreenRotationAlt
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem

@Composable
fun CropRotationSelector(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    EnhancedSliderItem(
        value = value,
        title = stringResource(R.string.rotation),
        modifier = modifier,
        icon = Icons.Rounded.ScreenRotationAlt,
        valueRange = -45f..45f,
        internalStateTransformation = { it.roundTo(1) },
        onValueChange = onValueChange
    )
}