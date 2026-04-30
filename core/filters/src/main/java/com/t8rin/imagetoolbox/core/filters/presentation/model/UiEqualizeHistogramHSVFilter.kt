/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiEqualizeHistogramHSVFilter(
    override val value: Float = 128f
) : UiFilter<Float>(
    title = R.string.equalize_histogram_hsv,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.bins_count, 2f..256f, 0)
    )
), Filter.EqualizeHistogramHSV