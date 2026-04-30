/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiBoxBlurFilter(
    override val value: Float = 10f,
) : UiFilter<Float>(
    title = R.string.box_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            valueRange = 0f..100f,
            roundTo = 0
        )
    )
), Filter.BoxBlur