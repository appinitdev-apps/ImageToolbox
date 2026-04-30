/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.image_cutting.domain

data class CutParams(
    val vertical: PivotPair?,
    val horizontal: PivotPair?,
    val inverseVertical: Boolean,
    val inverseHorizontal: Boolean
) {
    companion object {
        val Default by lazy {
            CutParams(
                vertical = null,
                horizontal = null,
                inverseVertical = false,
                inverseHorizontal = false
            )
        }
    }
}

class PivotPair(
    val start: Float,
    val end: Float,
    val isRtl: Boolean = false
) {
    val startRtlAdjusted = if (isRtl) 1f - end else start
    val endRtlAdjusted = if (isRtl) 1f - start else end

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PivotPair

        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }
}