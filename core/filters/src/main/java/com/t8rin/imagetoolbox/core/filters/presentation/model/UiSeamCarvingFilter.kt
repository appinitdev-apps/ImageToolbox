/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiSeamCarvingFilter(
    override val value: IntegerSize = IntegerSize.Zero
) : UiFilter<IntegerSize>(
    title = R.string.seam_carving,
    paramsInfo = listOf(
        FilterParam(R.string.width, 0f..0f),
        FilterParam(R.string.height, 0f..0f),
    ),
    value = value
), Filter.SeamCarving