/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiReplaceColorFilter(
    override val value: Triple<Float, ColorModel, ColorModel> = Triple(
        first = 0f,
        second = Color(red = 0.0f, green = 0.0f, blue = 0.0f, alpha = 1.0f).toModel(),
        third = Color(red = 1.0f, green = 1.0f, blue = 1.0f, alpha = 1.0f).toModel()
    ),
) : UiFilter<Triple<Float, ColorModel, ColorModel>>(
    title = R.string.replace_color,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.tolerance,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.color_to_replace,
            valueRange = 0f..0f
        ),
        FilterParam(
            title = R.string.target_color,
            valueRange = 0f..0f
        )
    )
), Filter.ReplaceColor