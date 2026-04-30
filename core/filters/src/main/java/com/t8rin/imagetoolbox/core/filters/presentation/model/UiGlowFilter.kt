/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiGlowFilter(
    override val value: Float = 0.5f
) : UiFilter<Float>(
    title = R.string.glow,
    value = value,
    paramsInfo = listOf(
        R.string.amount paramTo 0f..1f
    )
), Filter.Glow