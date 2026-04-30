/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.presentation.components

data class NeuralSaveProgress(
    val doneImages: Int,
    val totalImages: Int,
    val doneChunks: Int,
    val totalChunks: Int
) {
    val chunkProgress = if (totalChunks > 0) {
        doneChunks / totalChunks.toFloat()
    } else {
        0f
    }

    val totalProgress = if (totalImages > 0) {
        (doneImages.toFloat() + chunkProgress) / totalImages.toFloat()
    } else {
        0f
    }

    val isZero = doneImages == 0 && (totalImages < 2) && doneChunks == 0 && totalChunks == 0
}