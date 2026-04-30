/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.BorderStyle
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun BorderThicknessSettingItem(
    onValueChange: (Float) -> Unit,
    shape: Shape = ShapeDefaults.center,
    modifier: Modifier = Modifier
        .padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current
    var value by remember {
        mutableFloatStateOf(settingsState.borderWidth.value.coerceAtLeast(0f))
    }
    LaunchedEffect(value) {
        delay(500)
        onValueChange(value)
    }
    EnhancedSliderItem(
        modifier = modifier,
        shape = shape,
        valueSuffix = " Dp",
        value = value,
        title = stringResource(R.string.border_thickness),
        icon = Icons.Rounded.BorderStyle,
        onValueChange = {
            value = (it * 10).roundToInt() / 10f
        },
        internalStateTransformation = {
            (it * 10).roundToInt() / 10f
        },
        valueRange = 0f..1.5f,
        steps = 14
    )
}