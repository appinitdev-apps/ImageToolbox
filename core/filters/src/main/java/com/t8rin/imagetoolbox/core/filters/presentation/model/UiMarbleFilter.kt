/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiMarbleFilter(
    override val value: Triple<Float, Float, Float> = Triple(0.02f, 1f, 1f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.marble,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.turbulence,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.amplitude,
            valueRange = 0f..1f
        )
    )
), Filter.Marble