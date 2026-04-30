/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.LinearTiltShiftParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiLinearTiltShiftFilter(
    override val value: LinearTiltShiftParams = LinearTiltShiftParams.Default
) : UiFilter<LinearTiltShiftParams>(
    title = R.string.linear_tilt_shift,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.blur_radius, 1f..100f, 0),
        FilterParam(R.string.sigma, 1f..50f, 2),
        FilterParam(R.string.center_x, 0f..1f, 2),
        FilterParam(R.string.center_y, 0f..1f, 2),
        FilterParam(R.string.size, 0f..1f, 2),
        FilterParam(R.string.angle, 0f..360f, 0)
    )
), Filter.LinearTiltShift