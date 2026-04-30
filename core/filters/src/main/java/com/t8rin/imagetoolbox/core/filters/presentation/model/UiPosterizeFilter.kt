/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiPosterizeFilter(
    override val value: Float = 5f,
) : UiFilter<Float>(
    title = R.string.posterize,
    value = value,
    paramsInfo = listOf(
        FilterParam(null, 1f..40f, 0)
    )
), Filter.Posterize