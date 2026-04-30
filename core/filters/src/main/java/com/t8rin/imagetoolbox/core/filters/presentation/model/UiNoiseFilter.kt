/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiNoiseFilter(
    override val value: Float = 128f
) : UiFilter<Float>(
    title = R.string.noise,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            valueRange = 0f..255f,
            roundTo = 0
        )
    )
), Filter.Noise