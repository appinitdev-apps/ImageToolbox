/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.image_stitch.domain

import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.domain.image.model.ImageInfo
import com.t8rin.imagetoolbox.core.domain.image.model.ImageWithSize
import com.t8rin.imagetoolbox.core.domain.image.model.Quality
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize

interface ImageCombiner<I> {

    suspend fun combineImages(
        imageUris: List<String>,
        combiningParams: CombiningParams,
        onProgress: (Int) -> Unit
    ): Pair<I, ImageInfo>

    suspend fun calculateCombinedImageDimensions(
        imageUris: List<String>,
        combiningParams: CombiningParams
    ): IntegerSize

    suspend fun createCombinedImagesPreview(
        imageUris: List<String>,
        combiningParams: CombiningParams,
        imageFormat: ImageFormat,
        quality: Quality,
        onGetByteCount: (Long) -> Unit
    ): ImageWithSize<I?>

}