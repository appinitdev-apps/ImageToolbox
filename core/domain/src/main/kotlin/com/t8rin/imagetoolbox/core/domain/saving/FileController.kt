/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.saving

import com.t8rin.imagetoolbox.core.domain.image.Metadata
import com.t8rin.imagetoolbox.core.domain.image.MetadataProvider
import com.t8rin.imagetoolbox.core.domain.saving.io.Writeable
import com.t8rin.imagetoolbox.core.domain.saving.model.SaveResult
import com.t8rin.imagetoolbox.core.domain.saving.model.SaveTarget
import kotlinx.coroutines.flow.Flow

interface FileController : ObjectSaver, MetadataProvider {
    val defaultSavingPath: String

    suspend fun save(
        saveTarget: SaveTarget,
        keepOriginalMetadata: Boolean,
        oneTimeSaveLocationUri: String? = null,
    ): SaveResult

    fun getSize(uri: String): Long?

    fun clearCache(onComplete: (Long) -> Unit = {})

    fun getCacheSize(): Long

    suspend fun readBytes(uri: String): ByteArray

    suspend fun writeBytes(
        uri: String,
        block: suspend (Writeable) -> Unit,
    ): SaveResult

    suspend fun transferBytes(
        fromUri: String,
        toUri: String
    ): SaveResult

    suspend fun transferBytes(
        fromUri: String,
        to: Writeable
    ): SaveResult

    suspend fun writeMetadata(
        imageUri: String,
        metadata: Metadata?
    )

    suspend fun listFilesInDirectory(treeUri: String): List<String>

    fun listFilesInDirectoryAsFlow(treeUri: String): Flow<String>

    companion object {
        fun FileController.toMetadataProvider(): MetadataProvider =
            object : MetadataProvider by this {}
    }
}