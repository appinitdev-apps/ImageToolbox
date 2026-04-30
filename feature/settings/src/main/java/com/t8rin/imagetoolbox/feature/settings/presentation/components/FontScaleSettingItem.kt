/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.TextFields
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalResourceManager
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.other.InfoContainer
import kotlinx.collections.immutable.persistentMapOf

@Composable
fun FontScaleSettingItem(
    onValueChange: (Float) -> Unit,
    shape: Shape = ShapeDefaults.bottom,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current
    val resources = LocalResourceManager.current

    var sliderValue by remember(settingsState.fontScale) {
        mutableFloatStateOf(settingsState.fontScale ?: 0.45f)
    }

    EnhancedSliderItem(
        modifier = modifier,
        shape = shape,
        value = sliderValue,
        title = stringResource(R.string.font_scale),
        icon = Icons.Rounded.TextFields,
        onValueChange = {
            sliderValue = it.roundToTwoDigits()
        },
        internalStateTransformation = {
            it.roundToTwoDigits()
        },
        onValueChangeFinished = {
            onValueChange(
                if (sliderValue < 0.5f) 0f
                else sliderValue
            )
        },
        valueRange = 0.45f..1.5f,
        steps = 20,
        valuesPreviewMapping = remember {
            persistentMapOf(0.45f to resources.getString(R.string.defaultt))
        },
        valueTextTapEnabled = false,
        additionalContent = {
            AnimatedVisibility(
                visible = sliderValue > 1.2f,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoContainer(
                    text = stringResource(R.string.using_large_fonts_warn),
                    textAlign = TextAlign.Start,
                    containerColor = MaterialTheme.colorScheme.errorContainer.copy(0.4f),
                    contentColor = MaterialTheme.colorScheme.onErrorContainer.copy(0.7f),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    )
}