/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiAldridgeFilter(
    override val value: Pair<Float, Float> = 1f to 0.025f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.aldridge,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.exposure,
            valueRange = 0f..2f
        ),
        FilterParam(
            title = R.string.cutoff,
            valueRange = -1f..1f,
            roundTo = 3
        ),
    )
), Filter.Aldridge