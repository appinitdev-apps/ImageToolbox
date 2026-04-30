/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiEnhancedCirclePixelationFilter(
    override val value: Float = 32f,
) : UiFilter<Float>(
    title = R.string.enhanced_circle_pixelation,
    value = value,
    valueRange = 15f..200f
), Filter.EnhancedCirclePixelation