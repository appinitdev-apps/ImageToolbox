/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DITHERING)
class UiClustered8x8DitheringFilter(
    override val value: Boolean = false,
) : UiFilter<Boolean>(
    title = R.string.clustered_8x8_dithering,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.gray_scale,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.Clustered8x8Dithering