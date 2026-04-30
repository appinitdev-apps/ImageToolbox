/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiOpacityFilter(
    override val value: Float = 0.5f,
) : UiFilter<Float>(
    title = R.string.opacity,
    value = value,
    valueRange = 0f..1f
), Filter.Opacity