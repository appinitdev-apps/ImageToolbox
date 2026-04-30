/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.collage_maker.presentation.components

data class CollageParams(
    val spacing: Float = 10f,
    val cornerRadius: Float = 0f,
    val outputScaleRatio: Float = 2f,
    val disableRotation: Boolean = true,
    val enableSnapToBorders: Boolean = true
)