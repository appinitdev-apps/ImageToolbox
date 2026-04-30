/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Exercise
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalResourceManager
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import kotlinx.collections.immutable.persistentMapOf
import kotlin.math.roundToInt

@Composable
fun VibrationStrengthSettingItem(
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
        .padding(horizontal = 8.dp),
    shape: Shape = ShapeDefaults.default
) {
    val settingsState = LocalSettingsState.current
    val resources = LocalResourceManager.current

    EnhancedSliderItem(
        modifier = modifier,
        shape = shape,
        value = settingsState.hapticsStrength,
        title = stringResource(R.string.vibration_strength),
        icon = Icons.Outlined.Exercise,
        onValueChange = {
            onValueChange(it.roundToInt())
        },
        internalStateTransformation = {
            it.roundToInt()
        },
        valueRange = 0f..2f,
        valuesPreviewMapping = remember {
            persistentMapOf(0f to resources.getString(R.string.disabled))
        },
        steps = 1,
        valueTextTapEnabled = false
    )
}