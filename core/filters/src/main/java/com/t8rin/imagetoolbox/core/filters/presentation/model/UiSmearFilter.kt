/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.SmearParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiSmearFilter(
    override val value: SmearParams = SmearParams.Default
) : UiFilter<SmearParams>(
    title = R.string.smear,
    paramsInfo = listOf(
        FilterParam(R.string.angle, 0f..360f, 0),
        R.string.density paramTo 0f..1f,
        R.string.mix paramTo 0f..1f,
        FilterParam(R.string.distance, 0f..200f, 0),
        FilterParam(R.string.shape, 0f..3f, 0)
    ),
    value = value
), Filter.Smear