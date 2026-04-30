/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiThresholdFilter(
    override val value: Float = 128f,
) : UiFilter<Float>(
    title = R.string.luminance_threshold,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = null,
            valueRange = 0f..255f,
            roundTo = 0
        )
    )
), Filter.Threshold