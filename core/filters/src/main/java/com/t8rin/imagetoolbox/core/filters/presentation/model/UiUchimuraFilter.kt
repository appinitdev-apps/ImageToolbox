/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiUchimuraFilter(
    override val value: Float = 1f
) : UiFilter<Float>(
    title = R.string.uchimura,
    value = value,
    valueRange = 0f..2f
), Filter.Uchimura