/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.BlurEdgeMode
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiGaussianBlurFilter(
    override val value: Triple<Float, Float, BlurEdgeMode> = Triple(
        25f,
        10f,
        BlurEdgeMode.Reflect101
    ),
) : UiFilter<Triple<Float, Float, BlurEdgeMode>>(
    title = R.string.gaussian_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 0f..100f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.sigma,
            valueRange = 1f..100f
        ),
        FilterParam(
            title = R.string.edge_mode,
            valueRange = 0f..0f
        )
    )
), Filter.GaussianBlur