/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiOilFilter(
    override val value: Pair<Float, Float> = 4f to 1f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.oil,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 1f..20f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..4f,
            roundTo = 1
        )
    )
), Filter.Oil