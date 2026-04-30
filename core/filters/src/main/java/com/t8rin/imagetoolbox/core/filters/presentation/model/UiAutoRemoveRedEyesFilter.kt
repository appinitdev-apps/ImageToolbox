/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiAutoRemoveRedEyesFilter(
    override val value: Float = 150f
) : UiFilter<Float>(
    title = R.string.auto_remove_red_eyes,
    value = value,
    paramsInfo = listOf(
        R.string.threshold paramTo 0f..255f
    )
), Filter.AutoRemoveRedEyes