/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.model.FileModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiLensCorrectionFilter(
    override val value: Pair<Float, FileModel> = 1f to FileModel("")
) : UiFilter<Pair<Float, FileModel>>(
    title = R.string.lens_correction,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = -1f..3f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.target_lens_profile,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.LensCorrection