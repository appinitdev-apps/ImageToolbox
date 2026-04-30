/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiWhiteBalanceFilter(
    override val value: Pair<Float, Float> = 7000.0f to 100f,
) : UiFilter<Pair<Float, Float>>(
    title = R.string.white_balance,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.temperature, 1000f..10000f, 0),
        FilterParam(R.string.tint, -100f..100f, 2)
    )
), Filter.WhiteBalance