/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.remote

interface DownloadManager {
    suspend fun download(
        url: String,
        destinationPath: String,
        onStart: suspend () -> Unit = {},
        onProgress: suspend (DownloadProgress) -> Unit,
        onFinish: suspend (Throwable?) -> Unit = {}
    )

    suspend fun downloadZip(
        url: String,
        destinationPath: String,
        onStart: suspend () -> Unit = {},
        onProgress: (DownloadProgress) -> Unit,
        downloadOnlyNewData: Boolean
    )
}