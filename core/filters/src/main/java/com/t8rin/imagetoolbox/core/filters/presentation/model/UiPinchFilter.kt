/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.PinchParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiPinchFilter(
    override val value: PinchParams = PinchParams.Default
) : UiFilter<PinchParams>(
    title = R.string.whirl_and_pinch,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.angle, 0f..360f, 0),
        R.string.center_x paramTo 0f..1f,
        R.string.center_y paramTo 0f..1f,
        R.string.radius paramTo 0f..2f,
        R.string.amount paramTo -1f..1f
    )
), Filter.Pinch