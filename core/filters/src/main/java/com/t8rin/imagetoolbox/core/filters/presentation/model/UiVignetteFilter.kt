/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiVignetteFilter(
    override val value: Triple<Float, Float, ColorModel> = Triple(
        first = 0.3f,
        second = 0.75f,
        third = Color.Black.toModel()
    ),
) : UiFilter<Triple<Float, Float, ColorModel>>(
    title = R.string.vignette,
    value = value,
    paramsInfo = listOf(
        R.string.start paramTo -2f..2f,
        R.string.end paramTo -2f..2f,
        R.string.color paramTo 0f..0f
    )
), Filter.Vignette