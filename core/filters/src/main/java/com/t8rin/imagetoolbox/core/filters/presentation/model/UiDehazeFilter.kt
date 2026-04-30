/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiDehazeFilter(
    override val value: Pair<Float, Float> = 17f to 0.45f,
) : UiFilter<Pair<Float, Float>>(
    title = R.string.dehaze,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 1f..50f,
            roundTo = 0
        ),
        R.string.omega paramTo 0f..1f
    )
), Filter.Dehaze