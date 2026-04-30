/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.utils

import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.supervisorScope

suspend fun HttpClient.getWithProgress(
    url: String,
    onFailure: suspend (Throwable) -> Unit = {},
    onProgress: suspend (bytesSentTotal: Long, contentLength: Long?) -> Unit,
    onOpen: suspend (ByteReadChannel) -> Unit
) = supervisorScope {
    runSuspendCatching {
        prepareGet(
            urlString = url,
            block = {
                onDownload(onProgress)
            }
        ).execute { response ->
            onOpen(response.bodyAsChannel())
        }
    }.onFailure {
        onFailure(it)
    }
}