/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiClaheFilter(
    override val value: Triple<Float, Float, Float> = Triple(0.5f, 8f, 8f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.clahe,
    paramsInfo = listOf(
        FilterParam(R.string.threshold, -10f..10f, 2),
        FilterParam(R.string.grid_size_x, 1f..100f, 0),
        FilterParam(R.string.grid_size_y, 1f..100f, 0)
    ),
    value = value
), Filter.Clahe