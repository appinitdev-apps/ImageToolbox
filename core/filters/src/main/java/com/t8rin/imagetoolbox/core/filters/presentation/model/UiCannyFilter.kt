/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiCannyFilter(
    override val value: Pair<Float, Float> = 100f to 200f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.canny,
    value = value,
    paramsInfo = listOf(
        R.string.threshold_one paramTo 0f..1000f,
        R.string.threshold_two paramTo 0f..1000f
    )
), Filter.Canny