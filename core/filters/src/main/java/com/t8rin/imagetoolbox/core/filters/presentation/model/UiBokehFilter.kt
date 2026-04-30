/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiBokehFilter(
    override val value: Pair<Float, Float> = 6f to 6f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.bokeh,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.just_size,
            valueRange = 1f..150f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.amount,
            valueRange = 3f..40f,
            roundTo = 0
        )
    )
), Filter.Bokeh