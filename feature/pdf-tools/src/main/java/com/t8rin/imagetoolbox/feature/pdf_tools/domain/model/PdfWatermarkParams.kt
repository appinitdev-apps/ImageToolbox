/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

data class PdfWatermarkParams(
    val color: Int = 0x000000,
    val fontSize: Float = 50f,
    val rotation: Float = 315f,
    val opacity: Float = 0.3f,
    val pages: List<Int> = emptyList(),
    val text: String = "Watermark",
)