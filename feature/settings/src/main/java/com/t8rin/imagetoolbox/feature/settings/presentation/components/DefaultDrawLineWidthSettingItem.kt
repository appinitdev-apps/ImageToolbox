/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.LineWeight
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

@Composable
fun DefaultDrawLineWidthSettingItem(
    onValueChange: (Float) -> Unit,
    shape: Shape = ShapeDefaults.center,
    modifier: Modifier = Modifier
        .padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current

    var value by remember {
        mutableFloatStateOf(settingsState.defaultDrawLineWidth)
    }

    EnhancedSliderItem(
        modifier = modifier,
        shape = shape,
        value = value,
        title = stringResource(R.string.default_line_width),
        icon = Icons.Rounded.LineWeight,
        internalStateTransformation = {
            it.roundToTwoDigits()
        },
        onValueChange = {
            value = it
            onValueChange(it)
        },
        valueSuffix = " Pt",
        valueRange = 1f..100f
    )
}