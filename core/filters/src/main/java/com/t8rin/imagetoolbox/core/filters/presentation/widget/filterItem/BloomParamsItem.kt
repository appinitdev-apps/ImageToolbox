/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.widget.filterItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.utils.roundTo
import com.t8rin.imagetoolbox.core.filters.domain.model.params.BloomParams
import com.t8rin.imagetoolbox.core.filters.presentation.model.UiFilter
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import kotlin.math.roundToInt

@Composable
internal fun BloomParamsItem(
    value: BloomParams,
    filter: UiFilter<BloomParams>,
    onFilterChange: (value: BloomParams) -> Unit,
    previewOnly: Boolean
) {
    val threshold: MutableState<Float> =
        remember(value) { mutableFloatStateOf(value.threshold) }
    val intensity: MutableState<Float> =
        remember(value) { mutableFloatStateOf(value.intensity) }
    val radius: MutableState<Float> =
        remember(value) { mutableFloatStateOf(value.radius.toFloat()) }
    val softKnee: MutableState<Float> =
        remember(value) { mutableFloatStateOf(value.softKnee) }
    val exposure: MutableState<Float> =
        remember(value) { mutableFloatStateOf(value.exposure) }
    val gamma: MutableState<Float> =
        remember(value) { mutableFloatStateOf(value.gamma) }

    LaunchedEffect(
        threshold.value,
        intensity.value,
        radius.value,
        softKnee.value,
        exposure.value,
        gamma.value
    ) {
        onFilterChange(
            BloomParams(
                threshold = threshold.value,
                intensity = intensity.value,
                radius = radius.value.roundToInt(),
                softKnee = softKnee.value,
                exposure = exposure.value,
                gamma = gamma.value
            )
        )
    }

    val paramsInfo by remember(filter) {
        derivedStateOf {
            filter.paramsInfo.mapIndexedNotNull { index, filterParam ->
                if (filterParam.title == null) return@mapIndexedNotNull null
                when (index) {
                    0 -> threshold
                    1 -> intensity
                    2 -> radius
                    3 -> softKnee
                    4 -> exposure
                    else -> gamma
                } to filterParam
            }
        }
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        paramsInfo.forEach { (state, info) ->
            val (title, valueRange, roundTo) = info
            EnhancedSliderItem(
                enabled = !previewOnly,
                value = state.value,
                title = stringResource(title!!),
                valueRange = valueRange,
                onValueChange = {
                    state.value = it
                },
                internalStateTransformation = {
                    it.roundTo(roundTo)
                },
                behaveAsContainer = false
            )
        }
    }
}