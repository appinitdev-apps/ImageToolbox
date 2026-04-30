/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiMedianBlurFilter(
    override val value: Float = 10f
) : UiFilter<Float>(
    title = R.string.median_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.radius, 0f..100f, 0)
    )
), Filter.MedianBlur