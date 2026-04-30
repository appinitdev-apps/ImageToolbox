/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.BlurEdgeMode
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiFastGaussianBlur3DFilter(
    override val value: Pair<Float, BlurEdgeMode> = 10f to BlurEdgeMode.Reflect101
) : UiFilter<Pair<Float, BlurEdgeMode>>(
    title = R.string.fast_gaussian_blur_3d,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 1f..500f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.edge_mode,
            valueRange = 0f..0f
        )
    )
), Filter.FastGaussianBlur3D