/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.palette_tools.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Palette
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import kotlin.math.roundToInt

@Composable
fun PaletteColorsCountSelector(
    modifier: Modifier = Modifier,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    EnhancedSliderItem(
        modifier = modifier,
        value = value,
        icon = Icons.Rounded.Palette,
        title = stringResource(R.string.max_colors_count),
        onValueChange = {},
        internalStateTransformation = {
            it.roundToInt()
        },
        onValueChangeFinished = {
            onValueChange(it.roundToInt())
        },
        valueRange = 1f..128f,
        steps = 127,
        shape = ShapeDefaults.extraLarge
    )
}