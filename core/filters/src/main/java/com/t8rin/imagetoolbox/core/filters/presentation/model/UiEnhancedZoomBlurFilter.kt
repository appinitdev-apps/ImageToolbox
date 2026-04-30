/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.EnhancedZoomBlurParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.BLUR)
class UiEnhancedZoomBlurFilter(
    override val value: EnhancedZoomBlurParams = EnhancedZoomBlurParams.Default,
) : UiFilter<EnhancedZoomBlurParams>(
    title = R.string.enhanced_zoom_blur,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.radius, 1f..100f, 2),
        FilterParam(R.string.sigma, 1f..100f, 2),
        FilterParam(R.string.blur_center_x, 0f..1f, 2),
        FilterParam(R.string.blur_center_y, 0f..1f, 2),
        FilterParam(R.string.strength, 0f..3f, 2),
        FilterParam(R.string.angle, 0f..360f, 0)
    )
), Filter.EnhancedZoomBlur