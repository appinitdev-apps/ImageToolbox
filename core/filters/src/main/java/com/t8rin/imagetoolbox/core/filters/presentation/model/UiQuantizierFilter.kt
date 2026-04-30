/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DITHERING)
class UiQuantizierFilter(
    override val value: Float = 256f
) : UiFilter<Float>(
    title = R.string.quantizier,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = null,
            valueRange = 2f..4096f,
            roundTo = 0
        )
    )
), Filter.Quantizier