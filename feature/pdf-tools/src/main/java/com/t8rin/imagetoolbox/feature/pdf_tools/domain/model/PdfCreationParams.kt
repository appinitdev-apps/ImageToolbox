/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

import com.t8rin.imagetoolbox.core.domain.image.model.Preset

data class PdfCreationParams(
    val scaleSmallImagesToLarge: Boolean = false,
    val preset: Preset.Percentage = Preset.Original,
    val quality: Int = 85
)