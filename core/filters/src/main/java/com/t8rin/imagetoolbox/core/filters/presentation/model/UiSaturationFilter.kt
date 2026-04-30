/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiSaturationFilter(
    override val value: Pair<Float, Boolean> = 2f to true,
) : UiFilter<Pair<Float, Boolean>>(
    title = R.string.saturation,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..2f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.enable_tonemapping,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.Saturation