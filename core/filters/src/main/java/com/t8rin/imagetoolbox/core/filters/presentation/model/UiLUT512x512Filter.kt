/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.model.ImageModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LUT)
class UiLUT512x512Filter(
    override val value: Pair<Float, ImageModel> = 1f to ImageModel(R.drawable.lookup)
) : UiFilter<Pair<Float, ImageModel>>(
    title = R.string.lut512x512,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.target_lut_image,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.LUT512x512