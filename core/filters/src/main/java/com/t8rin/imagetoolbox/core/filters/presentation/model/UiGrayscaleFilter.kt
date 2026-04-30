/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiGrayscaleFilter(
    override val value: Triple<Float, Float, Float> = Triple(0.299f, 0.587f, 0.114f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.gray_scale,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.color_red,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.color_green,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.color_blue,
            valueRange = 0f..1f
        )
    )
), Filter.Grayscale