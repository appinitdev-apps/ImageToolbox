/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.utils.NEAREST_ODD_ROUNDING
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.TransferFunc
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiLinearTentBlurFilter(
    override val value: Pair<Float, TransferFunc> = 11f to TransferFunc.SRGB
) : UiFilter<Pair<Float, TransferFunc>>(
    title = R.string.linear_tent_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.sigma,
            valueRange = 1f..300f,
            roundTo = NEAREST_ODD_ROUNDING
        ),
        FilterParam(
            title = R.string.tag_transfer_function,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.LinearTentBlur