/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.data.utils

internal data class HocrWord(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
    val text: String
)

internal data class HocrPageBox(
    val width: Float,
    val height: Float
)

internal data class HocrData(
    val pageBox: HocrPageBox?,
    val words: List<HocrWord>
)