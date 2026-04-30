/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.domain

import com.t8rin.imagetoolbox.core.domain.model.IntegerSize

enum class WarpMode {
    MOVE,
    GROW,
    SHRINK,
    SWIRL_CW,
    SWIRL_CCW,
    MIXING
}

data class WarpStroke(
    val fromX: Float,
    val fromY: Float,
    val toX: Float,
    val toY: Float
) {
    fun scaleToFitCanvas(
        currentSize: IntegerSize,
        oldSize: IntegerSize
    ): WarpStroke {
        val sx = currentSize.width.toFloat() / oldSize.width
        val sy = currentSize.height.toFloat() / oldSize.height
        return copy(
            fromX = fromX * sx,
            fromY = fromY * sy,
            toX = toX * sx,
            toY = toY * sy
        )
    }
}