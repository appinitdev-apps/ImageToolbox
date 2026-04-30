/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiMobiusFilter(
    override val value: Triple<Float, Float, Float> = Triple(1f, 0.9f, 1f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.mobius,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.exposure,
            valueRange = 0f..2f
        ),
        FilterParam(
            title = R.string.transition,
            valueRange = -2f..2f
        ),
        FilterParam(
            title = R.string.peak,
            valueRange = -2f..2f
        )
    )
), Filter.Mobius