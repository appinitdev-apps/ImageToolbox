/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.TransferFunc
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiLinearStackBlurFilter(
    override val value: Pair<Int, TransferFunc> = 10 to TransferFunc.SRGB
) : UiFilter<Pair<Int, TransferFunc>>(
    title = R.string.linear_stack_blur,
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
        )
    )
), Filter.LinearStackBlur