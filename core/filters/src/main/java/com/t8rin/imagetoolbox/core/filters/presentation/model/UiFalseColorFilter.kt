/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiFalseColorFilter(
    override val value: Pair<ColorModel, ColorModel> = Color.Yellow.toModel() to Color.Magenta.toModel()
) : UiFilter<Pair<ColorModel, ColorModel>>(
    title = R.string.false_color,
    value = value,
), Filter.FalseColor