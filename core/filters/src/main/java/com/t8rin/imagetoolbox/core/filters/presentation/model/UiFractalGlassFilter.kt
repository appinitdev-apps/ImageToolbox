/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiFractalGlassFilter(
    override val value: Pair<Float, Float> = 0.02f to 0.02f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.fractal_glass,
    value = value,
    paramsInfo = listOf(
        R.string.strength paramTo 0f..1f,
        R.string.amplitude paramTo 0f..1f
    )
), Filter.FractalGlass