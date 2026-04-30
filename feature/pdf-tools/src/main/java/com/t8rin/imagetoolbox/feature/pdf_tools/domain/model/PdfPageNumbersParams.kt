/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

import com.t8rin.imagetoolbox.core.domain.model.Position

data class PdfPageNumbersParams(
    val labelFormat: String = "Page {n} of {total}",
    val position: Position = Position.BottomCenter,
    val color: Int = -7829368
)