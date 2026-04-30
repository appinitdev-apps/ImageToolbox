/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiDiffuseFilter(
    override val value: Float = 50f
) : UiFilter<Float>(
    title = R.string.diffuse,
    value = value,
    paramsInfo = listOf(
        R.string.scale paramTo 0f..500f
    )
), Filter.Diffuse