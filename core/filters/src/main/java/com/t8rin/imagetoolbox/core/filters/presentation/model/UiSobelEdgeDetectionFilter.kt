/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiSobelEdgeDetectionFilter(
    override val value: Float = 1f,
) : UiFilter<Float>(
    title = R.string.sobel_edge,
    value = value,
    paramsInfo = listOf(
        FilterParam(title = R.string.line_width, valueRange = 1f..25f, roundTo = 0)
    )
), Filter.SobelEdgeDetection