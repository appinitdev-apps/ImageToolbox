/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.toModel

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiTriToneFilter(
    override val value: Triple<ColorModel, ColorModel, ColorModel> = Triple(
        first = Color(0xFFFF003B).toModel(),
        second = Color(0xFF831111).toModel(),
        third = Color(0xFFFF0099).toModel()
    )
) : UiFilter<Triple<ColorModel, ColorModel, ColorModel>>(
    title = R.string.tri_tone,
    value = value,
), Filter.TriTone