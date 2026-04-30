/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiHorizontalWindStaggerFilter(
    override val value: Triple<Float, Int, ColorModel> = Triple(
        first = 0.2f,
        second = 90,
        third = Color.Transparent.toModel()
    )
) : UiFilter<Triple<Float, Int, ColorModel>>(
    title = R.string.horizontal_wind_stagger,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..100f,
            roundTo = 1
        ),
        FilterParam(
            title = R.string.amount,
            valueRange = 10f..200f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.color,
            valueRange = 0f..0f
        )
    )
), Filter.HorizontalWindStagger