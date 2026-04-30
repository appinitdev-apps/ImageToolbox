/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

data class PdfRemoveAnnotationParams(
    val pages: List<Int>? = null,
    val types: Set<PdfAnnotationType> = setOf(PdfAnnotationType.Link)
)