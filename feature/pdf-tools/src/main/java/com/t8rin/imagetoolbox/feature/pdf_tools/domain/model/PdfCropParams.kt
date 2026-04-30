/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

import com.t8rin.imagetoolbox.core.domain.model.RectModel

data class PdfCropParams(
    val pages: List<Int>? = null,
    val rect: RectModel = RectModel(
        left = 0.1f,
        right = 0.9f,
        top = 0.1f,
        bottom = 0.9f
    )
)