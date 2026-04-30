/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.KaleidoscopeParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiKaleidoscopeFilter(
    override val value: KaleidoscopeParams = KaleidoscopeParams.Default
) : UiFilter<KaleidoscopeParams>(
    title = R.string.kaleidoscope,
    paramsInfo = listOf(
        FilterParam(R.string.angle, 0f..360f, 0),
        FilterParam(R.string.secondary_angle, 0f..360f, 0),
        FilterParam(R.string.center_x, 0f..1f, 2),
        FilterParam(R.string.center_y, 0f..1f, 2),
        FilterParam(R.string.sides, 2f..12f, 0),
        FilterParam(R.string.radius, 0f..100f, 2),
    ),
    value = value
), Filter.Kaleidoscope