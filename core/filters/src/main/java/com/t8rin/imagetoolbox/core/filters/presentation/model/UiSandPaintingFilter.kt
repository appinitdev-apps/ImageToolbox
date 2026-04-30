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
class UiSandPaintingFilter(
    override val value: Triple<Int, Int, ColorModel> = Triple(5000, 50, Color.Black.toModel())
) : UiFilter<Triple<Int, Int, ColorModel>>(
    title = R.string.sand_painting,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 50f..75000f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.threshold,
            valueRange = 30f..90f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.background_color,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.SandPainting