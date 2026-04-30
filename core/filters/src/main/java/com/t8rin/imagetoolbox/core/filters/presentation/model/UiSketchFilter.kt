/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiSketchFilter(
    override val value: Float = 5f,
) : UiFilter<Float>(
    title = R.string.sketch,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = null,
            valueRange = 3f..9f,
            roundTo = 0
        )
    )
), Filter.Sketch