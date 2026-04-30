/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.WaterParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiWaterEffectFilter(
    override val value: WaterParams = WaterParams()
) : UiFilter<WaterParams>(
    title = R.string.water_effect,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.just_size, 0f..1f, 2),
        FilterParam(R.string.frequency_x, -4f..4f, 2),
        FilterParam(R.string.frequency_y, -4f..4f, 2),
        FilterParam(R.string.amplitude_x, -4f..4f, 2),
        FilterParam(R.string.amplitude_y, -4f..4f, 2)
    )
), Filter.WaterEffect