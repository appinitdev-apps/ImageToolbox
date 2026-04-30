/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.noise_generation.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.domain.utils.roundTo
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.RampLeft
import com.t8rin.imagetoolbox.core.resources.icons.SettingsEthernet
import com.t8rin.imagetoolbox.core.resources.icons.Waves
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.DataSelector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.noise_generation.domain.model.CellularDistanceFunction
import com.t8rin.imagetoolbox.noise_generation.domain.model.CellularReturnType
import com.t8rin.imagetoolbox.noise_generation.domain.model.DomainWarpType
import com.t8rin.imagetoolbox.noise_generation.domain.model.FractalType
import com.t8rin.imagetoolbox.noise_generation.domain.model.NoiseParams
import com.t8rin.imagetoolbox.noise_generation.domain.model.NoiseType
import kotlin.math.roundToInt

@Composable
fun NoiseParamsSelection(
    value: NoiseParams,
    onValueChange: (NoiseParams) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EnhancedSliderItem(
            value = value.seed,
            icon = Icons.Rounded.SettingsEthernet,
            title = stringResource(R.string.seed),
            valueRange = -10000f..10000f,
            internalStateTransformation = {
                it.roundToInt()
            },
            onValueChange = {
                onValueChange(value.copy(seed = it.toInt()))
            },
            shape = ShapeDefaults.extraLarge
        )
        EnhancedSliderItem(
            value = value.frequency,
            icon = Icons.Rounded.Waves,
            title = stringResource(R.string.frequency),
            valueRange = -0.5f..0.5f,
            internalStateTransformation = {
                it.roundTo(3)
            },
            onValueChange = {
                onValueChange(value.copy(frequency = it))
            },
            shape = ShapeDefaults.extraLarge
        )
        DataSelector(
            value = value.noiseType,
            onValueChange = {
                onValueChange(value.copy(noiseType = it))
            },
            entries = NoiseType.entries,
            title = stringResource(R.string.noise_type),
            titleIcon = null,
            itemContentText = {
                it.name
            },
            spanCount = 2,
            containerColor = Color.Unspecified
        )
        DataSelector(
            value = value.fractalType,
            onValueChange = {
                onValueChange(value.copy(fractalType = it))
            },
            entries = FractalType.entries,
            title = stringResource(R.string.fractal_type),
            titleIcon = null,
            itemContentText = {
                it.name
            },
            spanCount = 2,
            containerColor = Color.Unspecified
        )
        AnimatedVisibility(value.fractalType != FractalType.None) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EnhancedSliderItem(
                    value = value.fractalOctaves,
                    title = stringResource(R.string.octaves),
                    valueRange = 1f..5f,
                    steps = 3,
                    internalStateTransformation = {
                        it.roundToInt()
                    },
                    onValueChange = {
                        onValueChange(value.copy(fractalOctaves = it.toInt()))
                    },
                    shape = ShapeDefaults.extraLarge
                )
                EnhancedSliderItem(
                    value = value.fractalLacunarity,
                    title = stringResource(R.string.lacunarity),
                    valueRange = -50f..50f,
                    internalStateTransformation = {
                        it.roundToTwoDigits()
                    },
                    onValueChange = {
                        onValueChange(value.copy(fractalLacunarity = it))
                    },
                    shape = ShapeDefaults.extraLarge
                )
                EnhancedSliderItem(
                    value = value.fractalGain,
                    title = stringResource(R.string.gain),
                    valueRange = -10f..10f,
                    internalStateTransformation = {
                        it.roundToTwoDigits()
                    },
                    onValueChange = {
                        onValueChange(value.copy(fractalGain = it))
                    },
                    shape = ShapeDefaults.extraLarge
                )
                EnhancedSliderItem(
                    value = value.fractalWeightedStrength,
                    title = stringResource(R.string.weighted_strength),
                    valueRange = -3f..3f,
                    internalStateTransformation = {
                        it.roundToTwoDigits()
                    },
                    onValueChange = {
                        onValueChange(value.copy(fractalWeightedStrength = it))
                    },
                    shape = ShapeDefaults.extraLarge
                )
                AnimatedVisibility(value.fractalType == FractalType.PingPong) {
                    EnhancedSliderItem(
                        value = value.fractalPingPongStrength,
                        title = stringResource(R.string.ping_pong_strength),
                        valueRange = 0f..20f,
                        internalStateTransformation = {
                            it.roundToTwoDigits()
                        },
                        onValueChange = {
                            onValueChange(value.copy(fractalPingPongStrength = it))
                        },
                        shape = ShapeDefaults.extraLarge
                    )
                }
                AnimatedVisibility(value.noiseType == NoiseType.Cellular) {
                    Column {
                        DataSelector(
                            value = value.cellularDistanceFunction,
                            onValueChange = {
                                onValueChange(value.copy(cellularDistanceFunction = it))
                            },
                            entries = CellularDistanceFunction.entries,
                            title = stringResource(R.string.distance_function),
                            titleIcon = null,
                            itemContentText = {
                                it.name
                            },
                            spanCount = 2,
                            containerColor = Color.Unspecified
                        )
                        Spacer(Modifier.height(8.dp))
                        DataSelector(
                            value = value.cellularReturnType,
                            onValueChange = {
                                onValueChange(value.copy(cellularReturnType = it))
                            },
                            entries = CellularReturnType.entries,
                            title = stringResource(R.string.return_type),
                            titleIcon = null,
                            itemContentText = {
                                it.name
                            },
                            spanCount = 2,
                            containerColor = Color.Unspecified
                        )
                        Spacer(Modifier.height(8.dp))
                        EnhancedSliderItem(
                            value = value.cellularJitter,
                            title = stringResource(R.string.jitter),
                            valueRange = -10f..10f,
                            internalStateTransformation = {
                                it.roundToTwoDigits()
                            },
                            onValueChange = {
                                onValueChange(value.copy(cellularJitter = it))
                            },
                            shape = ShapeDefaults.extraLarge
                        )
                    }
                }
            }
        }
        DataSelector(
            value = value.domainWarpType,
            onValueChange = {
                onValueChange(value.copy(domainWarpType = it))
            },
            entries = DomainWarpType.entries,
            title = stringResource(R.string.domain_warp),
            titleIcon = null,
            itemContentText = {
                it.name
            },
            spanCount = 2,
            containerColor = Color.Unspecified
        )
        EnhancedSliderItem(
            value = value.domainWarpAmp,
            icon = Icons.Rounded.RampLeft,
            title = stringResource(R.string.amplitude),
            valueRange = -2000f..2000f,
            internalStateTransformation = {
                it.roundToTwoDigits()
            },
            onValueChange = {
                onValueChange(value.copy(domainWarpAmp = it))
            },
            shape = ShapeDefaults.extraLarge
        )
    }
}