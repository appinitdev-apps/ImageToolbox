/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiPoissonBlurFilter(
    override val value: Float = 10f,
) : UiFilter<Float>(
    title = R.string.poisson_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = null,
            valueRange = 3f..200f,
            roundTo = 0
        )
    )
), Filter.PoissonBlur