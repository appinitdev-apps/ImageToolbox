/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.ToneCurvesParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiToneCurvesFilter(
    override val value: ToneCurvesParams = ToneCurvesParams.Default
) : UiFilter<ToneCurvesParams>(
    title = R.string.tone_curves,
    paramsInfo = listOf(
        FilterParam(R.string.values, 0f..0f)
    ),
    value = value
), Filter.ToneCurves