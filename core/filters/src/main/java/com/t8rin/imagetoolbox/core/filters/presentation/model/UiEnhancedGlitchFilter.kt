/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.GlitchParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiEnhancedGlitchFilter(
    override val value: GlitchParams = GlitchParams()
) : UiFilter<GlitchParams>(
    title = R.string.enhanced_glitch,
    value = value,
    paramsInfo = listOf(
        FilterParam(R.string.channel_shift_x, -1f..1f, 2),
        FilterParam(R.string.channel_shift_y, -1f..1f, 2),
        FilterParam(R.string.corruption_size, 0f..1f, 2),
        FilterParam(R.string.amount, 1f..100f, 0),
        FilterParam(R.string.corruption_shift_x, -1f..1f, 2),
        FilterParam(R.string.corruption_shift_y, -1f..1f, 2)
    )
), Filter.EnhancedGlitch