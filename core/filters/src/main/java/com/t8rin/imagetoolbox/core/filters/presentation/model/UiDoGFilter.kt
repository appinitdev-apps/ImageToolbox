/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiDoGFilter(
    override val value: Pair<Float, Float> = 1f to 2f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.dog,
    value = value,
    paramsInfo = listOf(
        R.string.radius paramTo 0f..100f,
        R.string.second_radius paramTo 0f..100f
    )
), Filter.DoG