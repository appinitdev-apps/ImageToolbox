/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.domain

import com.t8rin.imagetoolbox.feature.erase_background.domain.model.BgModelType

interface AutoBackgroundRemover<I> {

    fun removeBackgroundFromImage(
        image: I,
        modelType: BgModelType,
        onSuccess: (I) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    suspend fun trimEmptyParts(
        image: I,
        emptyColor: Int? = null
    ): I

    fun cleanup()

}