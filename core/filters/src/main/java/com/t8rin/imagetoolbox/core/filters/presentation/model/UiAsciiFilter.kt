/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.AsciiParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiAsciiFilter(
    override val value: AsciiParams = AsciiParams.Default
) : UiFilter<AsciiParams>(
    title = R.string.ascii,
    paramsInfo = listOf(
        FilterParam(R.string.gradient, 0f..0f),
        FilterParam(R.string.font_size, 1f..100f),
        FilterParam(R.string.font, 0f..0f),
        FilterParam(R.string.background_color, 0f..0f),
        FilterParam(R.string.gray_scale, 0f..0f)
    ),
    value = value
), Filter.Ascii