/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.utils.NEAREST_ODD_ROUNDING
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.BlurEdgeMode
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiMotionBlurFilter(
    override val value: Triple<Int, Float, BlurEdgeMode> = Triple(25, 45f, BlurEdgeMode.Reflect101),
) : UiFilter<Triple<Int, Float, BlurEdgeMode>>(
    title = R.string.motion_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.just_size,
            valueRange = 0f..201f,
            roundTo = NEAREST_ODD_ROUNDING
        ),
        FilterParam(
            title = R.string.angle,
            valueRange = 0f..360f
        ),
        FilterParam(
            title = R.string.edge_mode,
            valueRange = 0f..0f
        )
    )
), Filter.MotionBlur