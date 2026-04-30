/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.data

import android.content.Context
import androidx.core.net.toUri
import com.t8rin.imagetoolbox.core.data.saving.io.StringReadable
import com.t8rin.imagetoolbox.core.data.saving.io.UriReadable
import com.t8rin.imagetoolbox.core.data.utils.computeFromReadable
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.model.HashingType
import com.t8rin.imagetoolbox.core.domain.saving.io.Readable
import com.t8rin.imagetoolbox.feature.checksum_tools.domain.ChecksumManager
import com.t8rin.imagetoolbox.feature.checksum_tools.domain.ChecksumSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AndroidChecksumManager @Inject constructor(
    @ApplicationContext private val context: Context,
    dispatchersHolder: DispatchersHolder
) : ChecksumManager, DispatchersHolder by dispatchersHolder {

    override suspend fun calculateChecksum(
        type: HashingType,
        source: ChecksumSource
    ): String = withContext(defaultDispatcher) {
        runCatching {
            type.computeFromReadable(source.toReadable())
        }.getOrDefault("")
    }

    override suspend fun compareChecksum(
        checksum: String,
        type: HashingType,
        source: ChecksumSource
    ): Boolean = coroutineScope {
        calculateChecksum(type, source) == checksum
    }

    private fun ChecksumSource.toReadable(): Readable = when (this) {
        is ChecksumSource.Text -> StringReadable(data)
        is ChecksumSource.Uri -> UriReadable(data.toUri(), context)
    }

}