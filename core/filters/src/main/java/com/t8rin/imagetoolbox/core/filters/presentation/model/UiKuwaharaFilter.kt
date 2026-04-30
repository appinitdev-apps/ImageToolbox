/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiKuwaharaFilter(
    override val value: Float = 9f,
) : UiFilter<Float>(
    title = R.string.kuwahara,
    value = value,
    paramsInfo = listOf(
        FilterParam(null, 0f..10f, 0)
    )
), Filter.Kuwahara