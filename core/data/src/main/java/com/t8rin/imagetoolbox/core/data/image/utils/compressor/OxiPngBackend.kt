/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image.utils.compressor

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.data.image.utils.ImageCompressorBackend
import com.t8rin.imagetoolbox.core.domain.image.model.Quality
import com.t8rin.trickle.Oxipng

internal data object OxiPngBackend : ImageCompressorBackend {

    override suspend fun compress(
        image: Bitmap,
        quality: Quality
    ): ByteArray = Oxipng.optimize(
        bitmap = image,
        options = Oxipng.SimpleOptions(
            level = quality.qualityValue.coerceIn(0..6)
        )
    )

}