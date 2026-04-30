/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.image

import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.domain.saving.io.Writeable

interface ShareProvider {

    suspend fun cacheByteArray(
        byteArray: ByteArray,
        filename: String
    ): String?

    suspend fun shareByteArray(
        byteArray: ByteArray,
        filename: String,
        onComplete: () -> Unit = {}
    )

    suspend fun cacheData(
        filename: String,
        writeData: suspend (Writeable) -> Unit,
    ): String?

    suspend fun cacheDataOrThrow(
        filename: String,
        writeData: suspend (Writeable) -> Unit,
    ): String

    suspend fun shareData(
        writeData: suspend (Writeable) -> Unit,
        filename: String,
        onComplete: () -> Unit = {}
    )

    suspend fun shareUri(
        uri: String,
        type: MimeType.Single? = null,
        onComplete: () -> Unit
    )

    suspend fun shareUris(
        uris: List<String>
    )

    fun shareText(
        value: String,
        onComplete: () -> Unit
    )

}