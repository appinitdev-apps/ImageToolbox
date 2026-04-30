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
import com.t8rin.imagetoolbox.core.resources.icons.LineWeight
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

@Composable
fun LineWidthSelector(
    modifier: Modifier,
    value: Float,
    title: String = stringResource(R.string.line_width),
    valueRange: ClosedFloatingPointRange<Float> = 1f..100f,
    color: Color = Color.Unspecified,
    onValueChange: (Float) -> Unit
) {
    EnhancedSliderItem(
        modifier = modifier,
        value = value,
        containerColor = color,
        icon = Icons.Rounded.LineWeight,
        title = title,
        valueSuffix = " Pt",
        sliderModifier = Modifier
            .padding(top = 14.dp, start = 12.dp, end = 12.dp, bottom = 10.dp),
        valueRange = valueRange,
        internalStateTransformation = {
            it.roundToTwoDigits()
        },
        onValueChange = {
            onValueChange(it.roundToTwoDigits())
        },
        shape = ShapeDefaults.extraLarge
    )
}