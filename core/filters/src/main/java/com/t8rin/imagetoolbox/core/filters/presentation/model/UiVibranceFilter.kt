/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiVibranceFilter(
    override val value: Float = 3f,
) : UiFilter<Float>(
    title = R.string.vibrance,
    value = value,
    valueRange = -5f..5f
), Filter.Vibrance