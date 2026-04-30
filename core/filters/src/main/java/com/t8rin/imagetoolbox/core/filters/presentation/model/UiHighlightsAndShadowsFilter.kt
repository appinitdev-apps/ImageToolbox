/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LIGHT)
class UiHighlightsAndShadowsFilter(
    override val value: Float = 0.25f
) : UiFilter<Float>(
    title = R.string.highlights_shadows,
    value = value,
    valueRange = 0f..2f
), Filter.HighlightsAndShadows