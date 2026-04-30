/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.utils.Quad
import com.t8rin.imagetoolbox.core.domain.utils.qto
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.PIXELATION)
class UiWeaveFilter(
    override val value: Quad<Float, Float, Float, Float> = 16f to 16f qto (6f to 6f)
) : UiFilter<Quad<Float, Float, Float, Float>>(
    title = R.string.weave,
    paramsInfo = listOf(
        R.string.x_width paramTo 0f..100f,
        R.string.y_wdth paramTo 0f..100f,
        R.string.x_gap paramTo 0f..100f,
        R.string.y_gap paramTo 0f..100f,
    ),
    value = value
), Filter.Weave