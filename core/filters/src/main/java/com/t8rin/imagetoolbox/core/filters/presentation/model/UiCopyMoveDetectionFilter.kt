/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiCopyMoveDetectionFilter(
    override val value: Pair<Float, Float> = 4f to 0f
) : UiFilter<Pair<Float, Float>>(
    title = R.string.copy_move_detection,
    paramsInfo = listOf(
        FilterParam(R.string.retain, 1f..40f),
        FilterParam(R.string.coefficent, 0f..1f),
    ),
    value = value
), Filter.CopyMoveDetection