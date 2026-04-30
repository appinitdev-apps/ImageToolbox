/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiHejlBurgessToneMappingFilter(
    override val value: Float = 1f,
) : UiFilter<Float>(
    title = R.string.heji_burgess_tone_mapping,
    value = value,
    valueRange = 0f..4f
), Filter.HejlBurgessToneMapping