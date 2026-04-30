/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiBlockGlitchFilter(
    override val value: Pair<Float, Float> = 0.02f to 0.5f,
) : UiFilter<Pair<Float, Float>>(
    title = R.string.block_glitch,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.block_size,
            valueRange = 0f..1f,
            roundTo = 3
        ),
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f,
            roundTo = 3
        ),
    )
), Filter.BlockGlitch