/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.zip.data

import android.content.Context
import androidx.core.net.toUri
import com.t8rin.imagetoolbox.core.data.saving.io.UriReadable
import com.t8rin.imagetoolbox.core.data.utils.outputStream
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ShareProvider
import com.t8rin.imagetoolbox.core.utils.createZip
import com.t8rin.imagetoolbox.core.utils.filename
import com.t8rin.imagetoolbox.core.utils.putEntry
import com.t8rin.imagetoolbox.feature.zip.domain.ZipManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AndroidZipManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val shareProvider: ShareProvider,
    dispatchersHolder: DispatchersHolder
) : DispatchersHolder by dispatchersHolder, ZipManager {

    override suspend fun zip(
        files: List<String>,
        onProgress: () -> Unit
    ): String = withContext(defaultDispatcher) {
        shareProvider.cacheData(
            writeData = { writeable ->
                writeable.outputStream().createZip { output ->
                    for (file in files) {
                        output.putEntry(
                            name = file.toUri().filename(context) ?: continue,
                            input = UriReadable(file.toUri(), context).stream
                        )
                        onProgress()
                    }
                }
            },
            filename = files.firstOrNull()?.toUri()?.filename() ?: "temp.zip"
        ) ?: throw IllegalArgumentException("Cached to null file")
    }

}