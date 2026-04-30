/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.RadialTiltShiftParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiRadialTiltShiftFilter(
    override val value: RadialTiltShiftParams = RadialTiltShiftParams.Default
) : UiFilter<RadialTiltShiftParams>(
    title = R.string.tilt_shift,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.blur_radius, 1f..100f, 0),
        FilterParam(R.string.sigma, 1f..50f, 2),
        FilterParam(R.string.center_x, 0f..1f, 2),
        FilterParam(R.string.center_y, 0f..1f, 2),
        FilterParam(R.string.radius, 0f..1f, 2)
    )
), Filter.RadialTiltShift