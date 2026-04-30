/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiDeskewFilter(
    override val value: Pair<Float, Boolean> = 15f to true
) : UiFilter<Pair<Float, Boolean>>(
    title = R.string.deskew,
    paramsInfo = listOf(
        FilterParam(R.string.max, 0f..89f, 0),
        FilterParam(R.string.allow_crop, 0f..0f, 0)
    ),
    value = value
), Filter.Deskew