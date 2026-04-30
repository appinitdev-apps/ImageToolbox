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
class UiStrokePixelationFilter(
    override val value: Pair<Float, ColorModel> = 20f to Color.Black.toModel(),
) : UiFilter<Pair<Float, ColorModel>>(
    title = R.string.stroke_pixelation,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.pixel_size,
            valueRange = 5f..200f
        ),
        FilterParam(
            title = R.string.background_color,
            valueRange = 0f..0f
        )
    )
), Filter.StrokePixelation