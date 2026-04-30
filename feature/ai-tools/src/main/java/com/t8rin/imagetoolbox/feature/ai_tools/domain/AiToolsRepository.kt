/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.domain

import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress
import com.t8rin.imagetoolbox.core.domain.saving.model.SaveResult
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralModel
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AiToolsRepository<Image> {
    val occupiedStorageSize: StateFlow<Long>

    val downloadedModels: StateFlow<List<NeuralModel>>

    val selectedModel: StateFlow<NeuralModel?>

    suspend fun selectModel(
        model: NeuralModel?,
        forced: Boolean = false
    ): Boolean

    fun downloadModel(
        model: NeuralModel
    ): Flow<DownloadProgress>

    suspend fun importModel(
        uri: String
    ): SaveResult

    suspend fun processImage(
        image: Image,
        listener: AiProgressListener,
        params: NeuralParams
    ): Image?

    suspend fun deleteModel(model: NeuralModel)

    fun cleanup()

}