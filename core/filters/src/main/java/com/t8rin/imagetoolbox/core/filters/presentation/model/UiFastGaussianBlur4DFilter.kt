/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiFastGaussianBlur4DFilter(
    override val value: Float = 10f
) : UiFilter<Float>(
    title = R.string.fast_gaussian_blur_4d,
    value = value,
    valueRange = 1f..100f
), Filter.FastGaussianBlur4D