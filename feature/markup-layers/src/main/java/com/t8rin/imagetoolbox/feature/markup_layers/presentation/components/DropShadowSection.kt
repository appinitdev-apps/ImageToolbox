/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Shadow
import com.t8rin.imagetoolbox.core.ui.theme.toColor
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ColorRowSelector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import com.t8rin.imagetoolbox.feature.markup_layers.domain.DropShadow

@Composable
internal fun DropShadowSection(
    shadow: DropShadow?,
    onShadowChange: (DropShadow?) -> Unit,
    onShadowChangeContinuously: (DropShadow) -> Unit,
    onContinuousEditFinished: () -> Unit,
    shape: androidx.compose.ui.graphics.Shape = ShapeDefaults.large
) {
    var haveShadow by rememberSaveable(shadow != null) {
        mutableStateOf(shadow != null)
    }

    LaunchedEffect(haveShadow, shadow) {
        val desiredShadow = if (haveShadow) {
            shadow ?: DropShadow.Default
        } else null

        if (shadow != desiredShadow) {
            onShadowChange(desiredShadow)
        }
    }

    PreferenceRowSwitch(
        title = stringResource(R.string.add_shadow),
        subtitle = stringResource(R.string.add_shadow_sub),
        shape = shape,
        containerColor = MaterialTheme.colorScheme.surface,
        startIcon = Icons.Outlined.Shadow,
        checked = haveShadow,
        onClick = { haveShadow = it },
        additionalContent = {
            AnimatedVisibility(
                visible = shadow != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                val resolvedShadow = shadow ?: DropShadow.Default

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    ColorRowSelector(
                        value = resolvedShadow.color.toColor(),
                        onValueChange = {
                            onShadowChange(
                                resolvedShadow.copy(
                                    color = it.toArgb()
                                )
                            )
                        },
                        title = stringResource(R.string.shadow_color),
                        modifier = Modifier.container(
                            shape = ShapeDefaults.top,
                            color = MaterialTheme.colorScheme.surfaceContainerLow
                        )
                    )
                    EnhancedSliderItem(
                        value = resolvedShadow.blurRadius,
                        title = stringResource(R.string.blur_radius),
                        internalStateTransformation = { it.roundToTwoDigits() },
                        onValueChange = {
                            onShadowChangeContinuously(
                                resolvedShadow.copy(
                                    blurRadius = it
                                )
                            )
                        },
                        onValueChangeFinished = { _ -> onContinuousEditFinished() },
                        valueRange = DropShadow.BlurRadiusRange,
                        shape = ShapeDefaults.center,
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                    EnhancedSliderItem(
                        value = resolvedShadow.offsetX,
                        title = stringResource(R.string.offset_x),
                        internalStateTransformation = { it.roundToTwoDigits() },
                        onValueChange = {
                            onShadowChangeContinuously(
                                resolvedShadow.copy(
                                    offsetX = it
                                )
                            )
                        },
                        onValueChangeFinished = { _ -> onContinuousEditFinished() },
                        valueRange = DropShadow.OffsetXRange,
                        shape = ShapeDefaults.center,
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                    EnhancedSliderItem(
                        value = resolvedShadow.offsetY,
                        title = stringResource(R.string.offset_y),
                        internalStateTransformation = { it.roundToTwoDigits() },
                        onValueChange = {
                            onShadowChangeContinuously(
                                resolvedShadow.copy(
                                    offsetY = it
                                )
                            )
                        },
                        onValueChangeFinished = { _ -> onContinuousEditFinished() },
                        valueRange = DropShadow.OffsetYRange,
                        shape = ShapeDefaults.bottom,
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                }
            }
        }
    )
}