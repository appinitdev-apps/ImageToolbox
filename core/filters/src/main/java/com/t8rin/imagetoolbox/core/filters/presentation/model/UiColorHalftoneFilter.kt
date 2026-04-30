/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.utils.Quad
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DITHERING)
class UiColorHalftoneFilter(
    override val value: Quad<Float, Float, Float, Float> = Quad(
        first = 2f,
        second = 108f,
        third = 162f,
        fourth = 90f
    )
) : UiFilter<Quad<Float, Float, Float, Float>>(
    title = R.string.color_halftone,
    paramsInfo = listOf(
        FilterParam(R.string.radius, 0f..50f, 2),
        FilterParam(R.string.cyan, 0f..360f, 0),
        FilterParam(R.string.magenta, 0f..360f, 0),
        FilterParam(R.string.yellow, 0f..360f, 0),
    ),
    value = value
), Filter.ColorHalftone