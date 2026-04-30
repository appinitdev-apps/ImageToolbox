/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiPolkaDotFilter(
    override val value: Triple<Int, Int, ColorModel> = Triple(
        first = 10,
        second = 8,
        third = Color.Black.toModel()
    )
) : UiFilter<Triple<Int, Int, ColorModel>>(
    title = R.string.polka_dot,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 1f..40f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.spacing,
            valueRange = 1f..40f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.background_color,
            valueRange = 0f..0f
        )
    )
), Filter.PolkaDot