/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DITHERING)
class UiCrosshatchFilter(
    override val value: Pair<Float, Float> = 0.01f to 0.003f,
) : UiFilter<Pair<Float, Float>>(
    title = R.string.crosshatch,
    value = value,
    paramsInfo = listOf(
        FilterParam(title = R.string.spacing, valueRange = 0.001f..0.05f, roundTo = 4),
        FilterParam(title = R.string.line_width, valueRange = 0.001f..0.02f, roundTo = 4)
    )
), Filter.Crosshatch