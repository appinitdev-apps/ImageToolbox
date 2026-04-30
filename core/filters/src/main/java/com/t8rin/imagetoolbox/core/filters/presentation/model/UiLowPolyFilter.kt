/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiLowPolyFilter(
    override val value: Pair<Float, Boolean> = 2000f to true
) : UiFilter<Pair<Float, Boolean>>(
    title = R.string.low_poly,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 50f..30000f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.fill,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.LowPoly