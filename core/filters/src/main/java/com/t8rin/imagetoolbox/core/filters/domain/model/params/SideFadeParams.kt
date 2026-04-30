/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.domain.model.params

import androidx.annotation.FloatRange
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.FadeSide

sealed class SideFadeParams(
    open val side: FadeSide
) {
    data class Relative(
        override val side: FadeSide,
        @FloatRange(0.0, 1.0)
        val scale: Float
    ) : SideFadeParams(side)

    data class Absolute(
        override val side: FadeSide,
        val size: Int,
        val strength: Float = 1f
    ) : SideFadeParams(side)
}