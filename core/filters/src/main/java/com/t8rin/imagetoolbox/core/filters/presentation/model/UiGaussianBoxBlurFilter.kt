/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiGaussianBoxBlurFilter(
    override val value: Float = 10f
) : UiFilter<Float>(
    title = R.string.gaussian_box_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.sigma,
            valueRange = 1f..300f,
            roundTo = 0
        )
    )
), Filter.GaussianBoxBlur