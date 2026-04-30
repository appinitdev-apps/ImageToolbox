/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiExposureFilter(
    override val value: Float = 1f,
) : UiFilter<Float>(
    title = R.string.exposure,
    value = value,
    valueRange = -4f..4f
), Filter.Exposure