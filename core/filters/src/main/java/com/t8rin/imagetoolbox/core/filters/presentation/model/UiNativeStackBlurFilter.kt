/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiNativeStackBlurFilter(
    override val value: Float = 25f,
) : UiFilter<Float>(
    title = R.string.native_stack_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = null,
            valueRange = 3f..250f,
            roundTo = 0
        )
    )
), Filter.NativeStackBlur