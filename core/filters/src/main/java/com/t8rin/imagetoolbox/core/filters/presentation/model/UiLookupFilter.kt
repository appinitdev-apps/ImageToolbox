/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiLookupFilter(
    override val value: Float = -1f,
) : UiFilter<Float>(
    title = R.string.lookup,
    value = value,
    valueRange = -10f..10f
), Filter.Lookup