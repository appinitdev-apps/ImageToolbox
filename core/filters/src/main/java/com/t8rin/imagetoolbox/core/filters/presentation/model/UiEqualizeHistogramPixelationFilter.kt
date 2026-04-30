/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiEqualizeHistogramPixelationFilter(
    override val value: Pair<Float, Float> = 50f to 50f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.equalize_histogram_pixelation,
    paramsInfo = listOf(
        FilterParam(R.string.grid_size_x, 1f..200f, 0),
        FilterParam(R.string.grid_size_y, 1f..200f, 0)
    ),
    value = value
), Filter.EqualizeHistogramPixelation