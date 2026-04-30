/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.SparkleParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiSparkleFilter(
    override val value: SparkleParams = SparkleParams.Default
) : UiFilter<SparkleParams>(
    title = R.string.sparkle,
    paramsInfo = listOf(
        FilterParam(R.string.amount, 0f..200f, 0),
        FilterParam(R.string.rays, 0f..200f, 0),
        FilterParam(R.string.radius, 0f..1f),
        FilterParam(R.string.randomness, 0f..100f, 0),
        FilterParam(R.string.center_x, 0f..1f),
        FilterParam(R.string.center_y, 0f..1f),
        FilterParam(R.string.color, 0f..0f)
    ),
    value = value
), Filter.Sparkle