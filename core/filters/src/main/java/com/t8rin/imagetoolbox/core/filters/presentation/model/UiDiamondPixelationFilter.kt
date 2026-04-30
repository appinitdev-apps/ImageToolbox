/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiDiamondPixelationFilter(
    override val value: Float = 24f,
) : UiFilter<Float>(
    title = R.string.diamond_pixelation,
    value = value,
    valueRange = 10f..200f
), Filter.DiamondPixelation