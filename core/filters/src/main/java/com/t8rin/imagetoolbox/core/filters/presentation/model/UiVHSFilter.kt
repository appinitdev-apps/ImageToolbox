/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import kotlin.math.PI

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiVHSFilter(
    override val value: Pair<Float, Float> = 2f to 3f,
) : UiFilter<Pair<Float, Float>>(
    title = R.string.vhs,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.seed,
            valueRange = 0f..PI.toFloat(),
            roundTo = 3
        ),
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..10f,
            roundTo = 3
        ),
    )
), Filter.VHS