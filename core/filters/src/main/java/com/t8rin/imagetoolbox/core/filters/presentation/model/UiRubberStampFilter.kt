/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.params.RubberStampParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiRubberStampFilter(
    override val value: RubberStampParams = RubberStampParams.Default
) : UiFilter<RubberStampParams>(
    title = R.string.rubber_stmp,
    paramsInfo = listOf(
        R.string.threshold paramTo 0f..1f,
        R.string.brush_softness paramTo 0f..1f,
        R.string.radius paramTo 0f..2f,
        R.string.first_color paramTo 0f..0f,
        R.string.second_color paramTo 0f..0f
    ),
    value = value
), Filter.RubberStamp