/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiAnisotropicDiffusionFilter(
    override val value: Triple<Float, Float, Float> = Triple(20f, 0.6f, 0.5f)
) : UiFilter<Triple<Float, Float, Float>>(
    title = R.string.anisotropic_diffusion,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.repeat_count,
            valueRange = 1f..100f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.conduction,
            valueRange = 0.1f..1f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.diffusion,
            valueRange = 0.01f..1f,
            roundTo = 2
        )
    )
), Filter.AnisotropicDiffusion