/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.data

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.feature.erase_background.data.backend.GenericBackgroundRemoverBackend
import com.t8rin.imagetoolbox.feature.erase_background.data.backend.MlKitBackgroundRemoverBackend
import com.t8rin.imagetoolbox.feature.erase_background.domain.AutoBackgroundRemoverBackend
import com.t8rin.imagetoolbox.feature.erase_background.domain.AutoBackgroundRemoverBackendFactory
import com.t8rin.imagetoolbox.feature.erase_background.domain.model.BgModelType
import javax.inject.Inject

internal class AndroidAutoBackgroundRemoverBackendFactory @Inject constructor() :
    AutoBackgroundRemoverBackendFactory<Bitmap> {

    override fun create(
        modelType: BgModelType
    ): AutoBackgroundRemoverBackend<Bitmap> = when (modelType) {
        BgModelType.MlKit -> MlKitBackgroundRemoverBackend
        else -> GenericBackgroundRemoverBackend(modelType)
    }

}