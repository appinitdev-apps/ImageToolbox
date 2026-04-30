/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.utils.Quad
import com.t8rin.imagetoolbox.core.domain.utils.qto
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiSwirlDistortionFilter(
    override val value: Quad<Float, Float, Float, Float> = 0.5f to 1f qto (0.5f to 0.5f),
) : UiFilter<Quad<Float, Float, Float, Float>>(
    title = R.string.swirl,
    value = value,
    paramsInfo = listOf(
        R.string.radius paramTo 0f..1f,
        R.string.angle paramTo -1f..1f,
        R.string.center_x paramTo 0.01f..1f,
        R.string.center_y paramTo 0.01f..1f
    )
), Filter.SwirlDistortion