/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

data class SearchablePdfPage(
    val imageUri: String,
    val text: String,
    val hocr: String
)