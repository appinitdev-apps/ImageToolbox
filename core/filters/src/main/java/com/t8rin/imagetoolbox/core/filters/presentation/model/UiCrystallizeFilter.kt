/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiCrystallizeFilter(
    override val value: Pair<Float, ColorModel> = 1f to Color.Transparent.toModel()
) : UiFilter<Pair<Float, ColorModel>>(
    title = R.string.crystallize,
    value = value,
    paramsInfo = listOf(
        R.string.amount paramTo 0.01f..2f,
        R.string.stroke_color paramTo 0f..0f
    )
), Filter.Crystallize