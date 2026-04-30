/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.domain.model.params

data class BloomParams(
    val threshold: Float,
    val intensity: Float,
    val radius: Int,
    val softKnee: Float,
    val exposure: Float,
    val gamma: Float
) {
    companion object {
        val Default = BloomParams(
            threshold = 0.6f,
            intensity = 1.5f,
            radius = 25,
            softKnee = 0.5f,
            exposure = 0.02f,
            gamma = 1.1f
        )
    }
}