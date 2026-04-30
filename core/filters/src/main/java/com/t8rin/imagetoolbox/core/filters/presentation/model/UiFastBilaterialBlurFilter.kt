/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.utils.NEAREST_ODD_ROUNDING
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiFastBilaterialBlurFilter(
    override val value: Triple<Float, Float, Float> = Triple(11f, 10f, 3f),
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.fast_bilaterial_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.just_size,
            valueRange = 1f..200f,
            roundTo = NEAREST_ODD_ROUNDING
        ),
        FilterParam(
            title = R.string.sigma,
            valueRange = 1f..100f,
            roundTo = 1
        ),
        FilterParam(
            title = R.string.spatial_sigma,
            valueRange = 1f..100f,
            roundTo = 1
        )
    )
), Filter.FastBilaterialBlur