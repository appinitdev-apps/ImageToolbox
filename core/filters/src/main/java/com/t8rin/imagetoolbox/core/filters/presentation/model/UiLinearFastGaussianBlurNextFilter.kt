/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.BlurEdgeMode
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.TransferFunc
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiLinearFastGaussianBlurNextFilter(
    override val value: Triple<Int, TransferFunc, BlurEdgeMode> = Triple(
        first = 10,
        second = TransferFunc.SRGB,
        third = BlurEdgeMode.Reflect101
    )
) : UiFilter<Triple<Int, TransferFunc, BlurEdgeMode>>(
    title = R.string.linear_fast_gaussian_blur_next,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 1f..300f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.tag_transfer_function,
            valueRange = 0f..0f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.edge_mode,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.LinearFastGaussianBlurNext