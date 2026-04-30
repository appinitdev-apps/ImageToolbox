/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.remote

import android.content.Context
import com.t8rin.imagetoolbox.core.data.utils.getWithProgress
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.remote.DownloadManager
import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress
import com.t8rin.imagetoolbox.core.domain.resource.ResourceManager
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService
import com.t8rin.imagetoolbox.core.domain.saving.track
import com.t8rin.imagetoolbox.core.domain.saving.updateProgress
import com.t8rin.imagetoolbox.core.domain.utils.throttleLatest
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.utils.decodeEscaped
import com.t8rin.imagetoolbox.core.utils.makeLog
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.utils.io.jvm.javaio.copyTo
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.inject.Inject
import kotlin.math.roundToInt

internal class AndroidDownloadManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val keepAliveService: KeepAliveService,
    private val client: HttpClient,
    resourceManager: ResourceManager,
    dispatchersHolder: DispatchersHolder
) : DownloadManager,
    ResourceManager by resourceManager,
    DispatchersHolder by dispatchersHolder {

    override suspend fun download(
        url: String,
        destinationPath: String,
        onStart: suspend () -> Unit,
        onProgress: suspend (DownloadProgress) -> Unit,
        onFinish: suspend (Throwable?) -> Unit
    ): Unit = withContext(defaultDispatcher) {
        keepAliveService.track(
            initial = {
                updateOrStart(
                    title = getString(R.string.downloading)
                )
            },
            onFailure = onFinish
        ) {
            channelFlow {
                onStart()
                url.makeLog("Start Download")

                val tmp = File(
                    File(context.cacheDir, "downloadCache").apply(File::mkdirs),
                    "${url.substringAfterLast('/').substringBefore('?')}.tmp"
                )

                client.getWithProgress(
                    url = url,
                    onFailure = onFinish,
                    onProgress = { bytesSentTotal, contentLength ->
                        val progress = contentLength?.let {
                            bytesSentTotal.toFloat() / contentLength.toFloat()
                        } ?: 0f

                        onProgress(
                            DownloadProgress(
                                currentPercent = progress,
                                currentTotalSize = contentLength ?: -1
                            ).also(::trySend)
                        )
                    },
                    onOpen = { response ->
                        tmp.outputStream().use { fos ->
                            response.copyTo(fos)
                            tmp.renameTo(File(destinationPath))
                            tmp.delete()
                            onFinish(null)
                        }
                    }
                )

                tmp.delete()
                close()
            }.throttleLatest(50).collect {
                updateProgress(
                    title = getString(R.string.downloading),
                    done = (it.currentPercent * 100).roundToInt(),
                    total = 100
                )
            }
        }
    }

    override suspend fun downloadZip(
        url: String,
        destinationPath: String,
        onStart: suspend () -> Unit,
        onProgress: (DownloadProgress) -> Unit,
        downloadOnlyNewData: Boolean
    ): Unit = withContext(defaultDispatcher) {
        keepAliveService.track(
            initial = {
                updateOrStart(
                    title = getString(R.string.downloading)
                )
            }
        ) {
            channelFlow {
                client.getWithProgress(
                    url = url,
                    onProgress = { bytesSentTotal, contentLength ->
                        val progress = contentLength?.let {
                            bytesSentTotal.toFloat() / contentLength.toFloat()
                        } ?: 0f

                        onProgress(
                            DownloadProgress(
                                currentPercent = progress,
                                currentTotalSize = contentLength ?: -1
                            ).also(::trySend)
                        )
                    },
                    onOpen = { response ->
                        ZipInputStream(response.toInputStream()).use { zipIn ->
                            var entry: ZipEntry?
                            while (zipIn.nextEntry.also { entry = it } != null) {
                                entry?.let { zipEntry ->
                                    val filename = zipEntry.name

                                    if (filename.isNullOrBlank() || filename.startsWith("__")) return@let

                                    val outFile = File(
                                        destinationPath,
                                        filename.decodeEscaped()
                                    ).apply {
                                        delete()
                                        parentFile?.mkdirs()
                                        createNewFile()
                                    }

                                    if (downloadOnlyNewData) {
                                        val file = File(destinationPath).listFiles()?.find {
                                            it.name == filename && it.length() > 0L
                                        }

                                        if (file != null) return@let
                                    }

                                    FileOutputStream(outFile).use { fos ->
                                        zipIn.copyTo(fos)
                                    }
                                    zipIn.closeEntry()
                                }
                            }
                        }
                    }
                )

                close()
            }.throttleLatest(50).collect {
                updateProgress(
                    title = getString(R.string.downloading),
                    done = (it.currentPercent * 100).roundToInt(),
                    total = 100
                )
            }
        }
    }

}