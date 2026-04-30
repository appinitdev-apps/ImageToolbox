/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiSmoothToonFilter(
    override val value: Triple<Float, Float, Float> = Triple(0.5f, 0.2f, 10f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.smooth_toon,
    value = value,
    paramsInfo = listOf(
        R.string.blur_size paramTo 0f..100f,
        R.string.threshold paramTo 0f..5f,
        R.string.quantizationLevels paramTo 0f..100f
    )
), Filter.SmoothToon