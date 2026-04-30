/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.SIMPLE)
class UiReduceNoiseFilter(
    override val value: Unit = Unit
) : UiFilter<Unit>(
    title = R.string.reduce_noise,
    value = value
), Filter.ReduceNoise