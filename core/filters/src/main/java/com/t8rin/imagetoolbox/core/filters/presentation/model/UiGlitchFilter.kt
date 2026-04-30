/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiGlitchFilter(
    override val value: Triple<Float, Float, Float> = Triple(20f, 15f, 9f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.glitch,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.amount,
            valueRange = 0f..100f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.seed,
            valueRange = 0f..100f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.repeat_count,
            valueRange = 0f..100f,
            roundTo = 0
        )
    )
), Filter.Glitch