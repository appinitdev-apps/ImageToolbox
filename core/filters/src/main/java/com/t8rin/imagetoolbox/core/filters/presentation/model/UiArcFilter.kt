/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.params.ArcParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiArcFilter(
    override val value: ArcParams = ArcParams.Default
) : UiFilter<ArcParams>(
    title = R.string.arc,
    paramsInfo = listOf(
        R.string.radius paramTo 0f..1f,
        R.string.just_size paramTo 0f..1f,
        R.string.angle paramTo 0f..360f,
        R.string.spread_angle paramTo 0f..360f,
        R.string.center_x paramTo 0f..1f,
        R.string.center_y paramTo 0f..1f
    ),
    value = value
), Filter.Arc