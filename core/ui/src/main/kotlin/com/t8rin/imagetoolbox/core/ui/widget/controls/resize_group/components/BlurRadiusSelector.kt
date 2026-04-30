/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.resize_group.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.BlurCircular
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import kotlin.math.roundToInt

@Composable
fun BlurRadiusSelector(
    modifier: Modifier,
    value: Int,
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
    valueRange: ClosedFloatingPointRange<Float> = 5f..100f,
    onValueChange: (Int) -> Unit,
    shape: Shape = ShapeDefaults.default
) {
    EnhancedSliderItem(
        modifier = modifier,
        value = value,
        title = stringResource(R.string.blur_radius),
        sliderModifier = Modifier
            .padding(
                top = 14.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 10.dp
            ),
        icon = Icons.Rounded.BlurCircular,
        valueRange = valueRange,
        internalStateTransformation = {
            it.roundToInt()
        },
        onValueChange = {
            onValueChange(it.roundToInt())
        },
        shape = shape,
        containerColor = color
    )
}