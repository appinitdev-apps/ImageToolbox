/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiAcesFilmicToneMappingFilter(
    override val value: Float = 1f,
) : UiFilter<Float>(
    title = R.string.aces_filmic_tone_mapping,
    value = value,
    valueRange = -4f..4f
), Filter.AcesFilmicToneMapping