/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiColorAnomalyFilter(
    override val value: Float = 0.56f
) : UiFilter<Float>(
    title = R.string.color_anomaly,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = null,
            valueRange = 0.56f..0.8f,
            roundTo = 3
        )
    )
), Filter.ColorAnomaly