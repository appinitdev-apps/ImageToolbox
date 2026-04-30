/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

data class PdfSignatureParams(
    val x: Float = 0.1f,
    val y: Float = 0.1f,
    val size: Float = 0.25f,
    val pages: List<Int> = emptyList(),
    val opacity: Float = 0.3f,
    val signatureImage: Any = "file:///android_asset/svg/emotions/aasparkles.svg"
)