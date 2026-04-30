/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiCrtCurvatureFilter(
    override val value: Triple<Float, Float, Float> = Triple(0.25f, 0.65f, 0.015f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.crt_curvature,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.curvature,
            valueRange = -1f..1f,
            roundTo = 3
        ),
        FilterParam(
            title = R.string.vignette,
            valueRange = 0f..1f,
            roundTo = 3
        ),
        FilterParam(
            title = R.string.chroma,
            valueRange = 0f..1f,
            roundTo = 3
        ),
    )
), Filter.CrtCurvature