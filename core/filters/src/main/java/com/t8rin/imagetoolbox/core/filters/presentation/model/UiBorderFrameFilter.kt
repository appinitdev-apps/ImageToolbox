/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiBorderFrameFilter(
    override val value: Triple<Float, Float, ColorModel> = Triple(20f, 40f, Color.White.toModel())
) : UiFilter<Triple<Float, Float, ColorModel>>(
    title = R.string.border_frame,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.horizontal_border_thickness,
            valueRange = 0f..500f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.vertical_border_thickness,
            valueRange = 0f..500f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.border_color,
            valueRange = 0f..0f
        )
    )
), Filter.BorderFrame