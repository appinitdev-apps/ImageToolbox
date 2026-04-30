/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiShuffleBlurFilter(
    override val value: Pair<Float, Float> = 35f to 1f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.shuffle_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 0f..70f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.threshold,
            valueRange = -1f..1f,
            roundTo = 2
        ),
    )
), Filter.ShuffleBlur