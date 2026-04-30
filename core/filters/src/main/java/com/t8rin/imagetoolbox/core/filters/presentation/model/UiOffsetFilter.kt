/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiOffsetFilter(
    override val value: Pair<Float, Float> = 0.25f to 0.25f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.offset,
    value = value,
    paramsInfo = listOf(
        R.string.offset_x paramTo 0f..1f,
        R.string.offset_y paramTo 0f..1f
    )
), Filter.Offset