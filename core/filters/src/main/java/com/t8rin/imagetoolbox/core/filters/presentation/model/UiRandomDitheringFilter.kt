/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DITHERING)
class UiRandomDitheringFilter(
    override val value: Pair<Float, Boolean> = 200f to false,
) : UiFilter<Pair<Float, Boolean>>(
    title = R.string.random_dithering,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.threshold,
            valueRange = 1f..255f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.gray_scale,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.RandomDithering