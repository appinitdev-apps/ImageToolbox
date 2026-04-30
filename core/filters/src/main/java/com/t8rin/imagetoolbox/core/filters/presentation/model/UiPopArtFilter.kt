/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.PopArtBlendingMode
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiPopArtFilter(
    override val value: Triple<Float, ColorModel, PopArtBlendingMode> = Triple(
        first = 1f,
        second = Color.Red.toModel(),
        third = PopArtBlendingMode.MULTIPLY
    )
) : UiFilter<Triple<Float, ColorModel, PopArtBlendingMode>>(
    title = R.string.pop_art,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.color,
            valueRange = 0f..1f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.overlay_mode,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.PopArt