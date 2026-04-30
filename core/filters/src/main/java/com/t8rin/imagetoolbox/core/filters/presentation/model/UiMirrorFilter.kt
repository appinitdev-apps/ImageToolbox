/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.MirrorSide
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiMirrorFilter(
    override val value: Pair<Float, MirrorSide> = 0.5f to MirrorSide.LeftToRight,
) : UiFilter<Pair<Float, MirrorSide>>(
    title = R.string.tile_mode_mirror,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.center,
            valueRange = 0f..1f
        ),
        FilterParam(
            title = R.string.side,
            valueRange = 0f..0f
        )
    )
), Filter.Mirror