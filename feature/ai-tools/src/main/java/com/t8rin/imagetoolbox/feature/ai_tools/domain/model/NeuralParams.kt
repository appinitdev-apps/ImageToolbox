/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.domain.model

data class NeuralParams(
    val strength: Float,
    val chunkSize: Int,
    val overlap: Int,
    val enableChunking: Boolean
) {
    companion object {
        val Default by lazy {
            NeuralParams(
                strength = 65f,
                chunkSize = 512,
                overlap = 16,
                enableChunking = true
            )
        }
    }
}