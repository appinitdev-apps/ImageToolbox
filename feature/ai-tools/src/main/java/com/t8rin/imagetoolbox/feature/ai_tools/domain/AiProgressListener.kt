/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.domain

interface AiProgressListener {
    fun onError(error: String)
    fun onProgress(currentChunkIndex: Int, totalChunks: Int)
}