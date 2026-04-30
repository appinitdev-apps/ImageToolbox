/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image.utils.compressor

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.data.image.utils.ImageCompressorBackend
import com.t8rin.imagetoolbox.core.domain.image.model.Quality
import com.t8rin.trickle.BmpCompressor

internal data object BmpBackend : ImageCompressorBackend {

    override suspend fun compress(
        image: Bitmap,
        quality: Quality
    ): ByteArray = runCatching {
        BmpCompressor.compress(image)
    }.getOrNull() ?: ByteArray(0)

}