/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.model.FileModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.LUT)
class UiCubeLutFilter(
    override val value: Pair<Float, FileModel> = 1f to FileModel("")
) : UiFilter<Pair<Float, FileModel>>(
    title = R.string.cube_lut,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.target_cube_lut_file,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.CubeLut