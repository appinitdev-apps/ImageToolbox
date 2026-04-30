/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.VoronoiCrystallizeParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiPointillizeFilter(
    override val value: VoronoiCrystallizeParams = VoronoiCrystallizeParams.Default
) : UiFilter<VoronoiCrystallizeParams>(
    title = R.string.pointillize,
    paramsInfo = listOf(
        FilterParam(R.string.border_thickness, 0f..5f, 2),
        FilterParam(R.string.scale, 1f..300f, 2),
        FilterParam(R.string.randomness, 0f..10f, 2),
        FilterParam(R.string.shape, 0f..4f, 0),
        FilterParam(R.string.turbulence, 0f..1f, 2),
        FilterParam(R.string.angle, 0f..360f, 0),
        FilterParam(R.string.stretch, 1f..6f, 2),
        FilterParam(R.string.amount, 0f..1f, 2),
        FilterParam(R.string.border_color, 0f..0f, 0),
    ),
    value = value
), Filter.Pointillize