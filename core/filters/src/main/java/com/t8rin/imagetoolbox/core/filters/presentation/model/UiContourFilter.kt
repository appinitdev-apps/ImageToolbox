/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.domain.utils.Quad
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiContourFilter(
    override val value: Quad<Float, Float, Float, ColorModel> = Quad(
        first = 5f,
        second = 1f,
        third = 0f,
        fourth = Color(0xff000000).toModel()
    )
) : UiFilter<Quad<Float, Float, Float, ColorModel>>(
    title = R.string.contour,
    paramsInfo = listOf(
        FilterParam(R.string.levels, 0f..50f, 2),
        FilterParam(R.string.scale, 0f..1f, 0),
        FilterParam(R.string.offset, 0f..255f, 0),
        FilterParam(R.string.color, 0f..0f, 0),
    ),
    value = value
), Filter.Contour