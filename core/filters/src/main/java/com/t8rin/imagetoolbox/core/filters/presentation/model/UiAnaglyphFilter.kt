/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiAnaglyphFilter(
    override val value: Float = 20f
) : UiFilter<Float>(
    title = R.string.anaglyph,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            valueRange = 0f..100f,
            roundTo = 0
        )
    )
), Filter.Anaglyph