/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.domain

import com.t8rin.imagetoolbox.feature.erase_background.domain.model.BgModelType

internal interface AutoBackgroundRemoverBackendFactory<I> {
    fun create(modelType: BgModelType): AutoBackgroundRemoverBackend<I>
}