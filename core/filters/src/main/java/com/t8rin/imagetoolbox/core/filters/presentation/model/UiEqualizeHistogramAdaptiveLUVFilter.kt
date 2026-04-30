/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiEqualizeHistogramAdaptiveLUVFilter(
    override val value: Triple<Float, Float, Float> = Triple(3f, 3f, 128f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.equalize_histogram_adaptive_luv,
    paramsInfo = listOf(
        FilterParam(R.string.grid_size_x, 1f..100f, 0),
        FilterParam(R.string.grid_size_y, 1f..100f, 0),
        FilterParam(R.string.bins_count, 2f..256f, 0)
    ),
    value = value
), Filter.EqualizeHistogramAdaptiveLUV