/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.image

import com.t8rin.imagetoolbox.core.domain.image.model.ImageData
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation

interface ImageGetter<I> {

    suspend fun getImage(
        uri: String,
        originalSize: Boolean = true,
        onFailure: ((Throwable) -> Unit)? = null
    ): ImageData<I>?

    fun getImageAsync(
        uri: String,
        originalSize: Boolean = true,
        onGetImage: (ImageData<I>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    suspend fun getImageWithTransformations(
        uri: String,
        transformations: List<Transformation<I>>,
        originalSize: Boolean = true
    ): ImageData<I>?

    suspend fun getImageWithTransformations(
        data: Any,
        transformations: List<Transformation<I>>,
        size: IntegerSize?
    ): I?

    suspend fun getImage(
        data: Any,
        originalSize: Boolean = true
    ): I?

    suspend fun getImage(
        data: Any,
        size: IntegerSize?
    ): I?

    suspend fun getImage(
        data: Any,
        size: Int?
    ): I?

    suspend fun getImageData(
        uri: String,
        size: Int?,
        onFailure: (Throwable) -> Unit
    ): ImageData<I>?

    fun getExtension(
        uri: String
    ): String?

}