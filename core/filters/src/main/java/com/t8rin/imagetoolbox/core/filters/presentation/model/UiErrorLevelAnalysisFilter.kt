/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.EFFECTS)
class UiErrorLevelAnalysisFilter(
    override val value: Float = 90f
) : UiFilter<Float>(
    title = R.string.error_level_analysis,
    paramsInfo = listOf(
        FilterParam(R.string.quality, 0f..100f, roundTo = 0),
    ),
    value = value
), Filter.ErrorLevelAnalysis