/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.domain.model.ImageModel
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.PaletteTransferSpace
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiPaletteTransferVariantFilter(
    override val value: Triple<Float, PaletteTransferSpace, ImageModel> = Triple(
        first = 1f,
        second = PaletteTransferSpace.OKLAB,
        third = ImageModel(R.drawable.filter_preview_source_2)
    )
) : UiFilter<Triple<Float, PaletteTransferSpace, ImageModel>>(
    title = R.string.palette_transfer_variant,
    value = value,
    paramsInfo = listOf(
        FilterParam(
            title = R.string.strength,
            valueRange = 0f..1f,
            roundTo = 2
        ),
        FilterParam(
            title = R.string.tag_color_space,
            valueRange = 0f..1f,
            roundTo = 0
        ),
        FilterParam(
            title = R.string.target_image,
            valueRange = 0f..0f,
            roundTo = 0
        )
    )
), Filter.PaletteTransferVariant