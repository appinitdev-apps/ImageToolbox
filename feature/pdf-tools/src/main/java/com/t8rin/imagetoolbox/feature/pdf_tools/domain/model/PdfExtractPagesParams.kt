/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

import com.t8rin.imagetoolbox.core.domain.image.model.Preset

data class PdfExtractPagesParams(
    val pages: List<Int>? = null,
    val preset: Preset.Percentage = Preset.Original
)