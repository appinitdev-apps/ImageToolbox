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
class UiNeonFilter(
    override val value: Triple<Float, Float, ColorModel> = Triple(
        first = 1f,
        second = 0.26f,
        third = Color.Magenta.toModel()
    )
) : UiFilter<Triple<Float, Float, ColorModel>>(
    title = R.string.neon,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.amount,
            valueRange = 1f..25f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.color,
            valueRange = 0f..0f
        )
    )
), Filter.Neon