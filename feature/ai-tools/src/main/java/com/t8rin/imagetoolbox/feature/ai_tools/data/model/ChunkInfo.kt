/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.data.model

import java.io.File

internal data class ChunkInfo(
    val index: Int,
    val inputFile: File,
    val processedFile: File,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val col: Int,
    val row: Int
)