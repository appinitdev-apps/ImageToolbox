/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.remote

import android.content.Context
import androidx.core.net.toUri
import com.t8rin.imagetoolbox.core.domain.RES_BASE_URL
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.remote.DownloadManager
import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress
import com.t8rin.imagetoolbox.core.domain.remote.RemoteResource
import com.t8rin.imagetoolbox.core.domain.remote.RemoteResources
import com.t8rin.imagetoolbox.core.domain.remote.RemoteResourcesStore
import com.t8rin.imagetoolbox.core.domain.resource.ResourceManager
import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import com.t8rin.imagetoolbox.core.utils.decodeEscaped
import com.t8rin.imagetoolbox.core.utils.makeLog
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject


internal class AndroidRemoteResourcesStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val downloadManager: DownloadManager,
    resourceManager: ResourceManager,
    dispatchersHolder: DispatchersHolder
) : RemoteResourcesStore,
    DispatchersHolder by dispatchersHolder,
    ResourceManager by resourceManager {

    override suspend fun getResources(
        name: String,
        forceUpdate: Boolean,
        onDownloadRequest: suspend (name: String) -> RemoteResources?
    ): RemoteResources? = withContext(ioDispatcher) {
        val availableFiles = getSavingDir(name).listFiles()
        val shouldDownload = forceUpdate || availableFiles.isNullOrEmpty()

        val availableResources = if (!availableFiles.isNullOrEmpty()) {
            RemoteResources(
                name = name,
                list = availableFiles.mapNotNull {
                    it.toUri().toString()
                }.map { uri ->
                    RemoteResource(
                        uri = uri,
                        name = uri.takeLastWhile { it != '/' }.decodeEscaped()
                    )
                }.sortedBy { it.name }
            )
        } else null

        if (shouldDownload) onDownloadRequest(name) ?: availableResources
        else availableResources
    }

    override suspend fun downloadResources(
        name: String,
        onProgress: (DownloadProgress) -> Unit,
        onFailure: (Throwable) -> Unit,
        downloadOnlyNewData: Boolean
    ): RemoteResources? = withContext(defaultDispatcher) {
        runSuspendCatching {
            val url = getResourcesLink(name)
            val savingDir = getSavingDir(name)

            downloadManager.downloadZip(
                url = url,
                destinationPath = savingDir.absolutePath,
                onProgress = onProgress,
                downloadOnlyNewData = downloadOnlyNewData
            )

            name to url makeLog "downloadResources"

            val savedAlready = savingDir.listFiles()?.mapNotNull {
                it.toUri().toString()
            }?.map { uri ->
                RemoteResource(
                    uri = uri,
                    name = uri.takeLastWhile { it != '/' }.decodeEscaped()
                )
            } ?: emptyList()

            RemoteResources(
                name = name,
                list = savedAlready.sortedBy { it.name }
            )
        }.onFailure {
            it.makeLog()
            onFailure(it)
        }.getOrNull()
    }

    private fun getResourcesLink(
        dirName: String
    ): String = RES_BASE_URL.replace("*", dirName)


    private fun getSavingDir(
        dirName: String
    ): File = File(rootDir, dirName.substringBefore("/")).apply(File::mkdirs)

    private val rootDir = File(context.filesDir, "remoteResources").apply(File::mkdirs)

}