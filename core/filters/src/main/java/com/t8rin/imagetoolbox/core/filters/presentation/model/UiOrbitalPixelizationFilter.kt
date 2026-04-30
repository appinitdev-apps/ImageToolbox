/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiOrbitalPixelizationFilter(
    override val value: Float = 25f,
) : UiFilter<Float>(
    title = R.string.orbital_pixelization,
    value = value,
    valueRange = 5f..200f
), Filter.OrbitalPixelation