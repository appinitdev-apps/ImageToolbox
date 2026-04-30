/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.BilaterialBlurParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiBilaterialBlurFilter(
    override val value: BilaterialBlurParams = BilaterialBlurParams.Default,
) : UiFilter<BilaterialBlurParams>(
    title = R.string.bilaterial_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.radius,
            valueRange = 1f..50f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.sigma,
            valueRange = 1f..100f,
            roundTo = 1
        ),
        FilterParam(
            title = R.string.spatial_sigma,
            valueRange = 1f..100f,
            roundTo = 1
        ),
        FilterParam(
            title = R.string.edge_mode,
            valueRange = 0f..0f,
            roundTo = 0
        ),
    )
), Filter.BilaterialBlur