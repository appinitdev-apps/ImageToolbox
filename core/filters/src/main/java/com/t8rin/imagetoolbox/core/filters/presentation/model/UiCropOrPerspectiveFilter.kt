/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.CropOrPerspectiveParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiCropOrPerspectiveFilter(
    override val value: CropOrPerspectiveParams = CropOrPerspectiveParams.Default
) : UiFilter<CropOrPerspectiveParams>(
    title = R.string.crop_or_perspective,
    paramsInfo = listOf(
        FilterParam(R.string.top_left, 0f..0f),
        FilterParam(R.string.top_right, 0f..0f),
        FilterParam(R.string.bottom_left, 0f..0f),
        FilterParam(R.string.bottom_right, 0f..0f),
        FilterParam(R.string.absolute, 0f..0f),
    ),
    value = value
), Filter.CropOrPerspective