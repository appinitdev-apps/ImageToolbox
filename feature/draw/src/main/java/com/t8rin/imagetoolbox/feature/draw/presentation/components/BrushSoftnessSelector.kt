/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Dots
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

@Composable
fun BrushSoftnessSelector(
    modifier: Modifier,
    value: Float,
    color: Color = Color.Unspecified,
    onValueChange: (Float) -> Unit
) {
    EnhancedSliderItem(
        modifier = modifier,
        value = value,
        title = stringResource(R.string.brush_softness),
        valueRange = 0f..100f,
        onValueChange = {
            onValueChange(it.roundToTwoDigits())
        },
        valueSuffix = " Pt",
        sliderModifier = Modifier
            .padding(
                top = 14.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 10.dp
            ),
        icon = Icons.Rounded.Dots,
        shape = ShapeDefaults.extraLarge,
        containerColor = color
    )
}