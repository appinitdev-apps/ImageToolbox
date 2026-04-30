/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.domain

data class DrawOnBackgroundParams(
    val width: Int,
    val height: Int,
    val color: Int?,
) {
    companion object {
        val Default by lazy {
            DrawOnBackgroundParams(
                width = -1,
                height = -1,
                color = null
            )
        }
    }
}