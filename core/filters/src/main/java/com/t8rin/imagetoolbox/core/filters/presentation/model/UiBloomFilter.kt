/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.BloomParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiBloomFilter(
    override val value: BloomParams = BloomParams.Default
) : UiFilter<BloomParams>(
    title = R.string.bloom,
    paramsInfo = listOf(
        FilterParam(R.string.threshold, 0f..1f),
        FilterParam(R.string.strength, 0f..3f),
        FilterParam(R.string.radius, 1f..100f, roundTo = 0),
        FilterParam(R.string.soft_knee, 0f..1f),
        FilterParam(R.string.exposure, 0f..1f),
        FilterParam(R.string.gamma, 0f..2f)
    ),
    value = value
), Filter.Bloom