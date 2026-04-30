/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiErodeFilter(
    override val value: Pair<Float, Boolean> = 25f to true
) : UiFilter<Pair<Float, Boolean>>(
    title = R.string.erode,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.just_size,
            valueRange = 1f..150f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.use_circle_kernel,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.Erode