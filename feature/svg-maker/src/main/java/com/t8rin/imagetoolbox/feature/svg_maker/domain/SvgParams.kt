/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.svg_maker.domain

data class SvgParams(
    val colorsCount: Int,
    val isPaletteSampled: Boolean,
    val quantizationCyclesCount: Int,
    val seed: Int,
    val blurRadius: Int,
    val blurDelta: Int,
    val pathOmit: Int,
    val linesThreshold: Float,
    val quadraticThreshold: Float,
    val minColorRatio: Float,
    val coordinatesRoundingAmount: Int,
    val svgPathsScale: Float, // 0.01f, 100f
    val isImageSampled: Boolean
) {
    companion object {
        val Default by lazy {
            SvgParams(
                colorsCount = 16,
                isPaletteSampled = true,
                quantizationCyclesCount = 3,
                seed = 0,
                blurRadius = 0,
                blurDelta = 20,
                pathOmit = 8,
                linesThreshold = 1f,
                quadraticThreshold = 1f,
                minColorRatio = 0.02f,
                coordinatesRoundingAmount = 1,
                svgPathsScale = 1f,
                isImageSampled = true
            )
        }
        val Detailed by lazy {
            Default.copy(
                pathOmit = 0,
                linesThreshold = 0.5f,
                quadraticThreshold = 0.5f,
                coordinatesRoundingAmount = 3,
                colorsCount = 64,
                quantizationCyclesCount = 1
            )
        }
        val Grayscale by lazy {
            Default.copy(
                isPaletteSampled = false,
                quantizationCyclesCount = 1,
                colorsCount = 7
            )
        }

        val presets by lazy {
            listOf(Default, Detailed, Grayscale)
        }
    }
}