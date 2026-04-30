/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.sliders

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.theme.blend
import com.t8rin.imagetoolbox.core.ui.theme.outlineVariant
import com.t8rin.imagetoolbox.core.ui.theme.takeColorFromScheme
import com.t8rin.imagetoolbox.core.ui.utils.animation.animateFloatingRangeAsState
import com.t8rin.imagetoolbox.core.ui.utils.provider.SafeLocalContainerColor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.sliders.custom_slider.CustomRangeSlider
import com.t8rin.imagetoolbox.core.ui.widget.sliders.custom_slider.CustomSlider
import com.t8rin.imagetoolbox.core.ui.widget.sliders.custom_slider.CustomSliderDefaults

@Composable
fun M2Slider(
    value: Float,
    enabled: Boolean,
    colors: SliderColors,
    interactionSource: MutableInteractionSource,
    modifier: Modifier,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (() -> Unit)?,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    drawContainer: Boolean = true
) {
    val settingsState = LocalSettingsState.current
    CustomSlider(
        interactionSource = interactionSource,
        enabled = enabled,
        modifier = modifier
            .then(
                if (drawContainer) {
                    Modifier
                        .container(
                            shape = ShapeDefaults.circle,
                            autoShadowElevation = animateDpAsState(
                                if (settingsState.drawSliderShadows) {
                                    1.dp
                                } else 0.dp
                            ).value,
                            resultPadding = 0.dp,
                            borderColor = MaterialTheme.colorScheme
                                .outlineVariant(
                                    luminance = 0.1f,
                                    onTopOf = SwitchDefaults.colors().disabledCheckedTrackColor
                                )
                                .copy(0.3f),
                            color = SafeLocalContainerColor
                                .copy(0.3f)
                                .compositeOver(
                                    takeColorFromScheme {
                                        if (it) {
                                            tertiaryContainer
                                                .blend(
                                                    secondaryContainer,
                                                    0.5f
                                                )
                                                .copy(0.1f)
                                        } else {
                                            secondaryContainer
                                                .blend(
                                                    tertiaryContainer,
                                                    0.3f
                                                )
                                                .copy(0.2f)
                                        }
                                    }
                                )
                                .copy(colors.activeTrackColor.alpha),
                            composeColorOnTopOfBackground = false
                        )
                        .padding(horizontal = 12.dp)
                } else Modifier
            ),
        value = value,
        colors = colors.toCustom(),
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        valueRange = valueRange,
        steps = steps,
        track = {
            CustomSliderDefaults.Track(
                sliderState = it,
                colors = colors.toCustom(),
                trackHeight = 4.dp,
                enabled = enabled
            )
        }
    )
}

@Composable
fun M2RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    enabled: Boolean,
    colors: SliderColors,
    startInteractionSource: MutableInteractionSource,
    endInteractionSource: MutableInteractionSource,
    modifier: Modifier,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onValueChangeFinished: (() -> Unit)?,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    drawContainer: Boolean = true
) {
    val settingsState = LocalSettingsState.current
    CustomRangeSlider(
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        enabled = enabled,
        modifier = modifier
            .then(
                if (drawContainer) {
                    Modifier
                        .container(
                            shape = ShapeDefaults.circle,
                            autoShadowElevation = animateDpAsState(
                                if (settingsState.drawSliderShadows) {
                                    1.dp
                                } else 0.dp
                            ).value,
                            resultPadding = 0.dp,
                            borderColor = MaterialTheme.colorScheme
                                .outlineVariant(
                                    luminance = 0.1f,
                                    onTopOf = SwitchDefaults.colors().disabledCheckedTrackColor
                                )
                                .copy(0.3f),
                            color = SafeLocalContainerColor
                                .copy(0.3f)
                                .compositeOver(
                                    takeColorFromScheme {
                                        if (it) {
                                            tertiaryContainer
                                                .blend(
                                                    secondaryContainer,
                                                    0.5f
                                                )
                                                .copy(0.1f)
                                        } else {
                                            secondaryContainer
                                                .blend(
                                                    tertiaryContainer,
                                                    0.3f
                                                )
                                                .copy(0.2f)
                                        }
                                    }
                                )
                                .copy(colors.activeTrackColor.alpha),
                            composeColorOnTopOfBackground = false
                        )
                        .padding(horizontal = 12.dp)
                } else Modifier
            ),
        value = animateFloatingRangeAsState(value).value,
        colors = colors.toCustom(),
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        valueRange = valueRange,
        steps = steps,
        track = {
            CustomSliderDefaults.Track(
                rangeSliderState = it,
                colors = colors.toCustom(),
                trackHeight = 4.dp,
                enabled = enabled
            )
        }
    )
}