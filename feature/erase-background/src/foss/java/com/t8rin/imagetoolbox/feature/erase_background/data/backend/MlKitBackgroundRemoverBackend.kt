/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.data.backend

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.feature.erase_background.domain.AutoBackgroundRemoverBackend
import com.t8rin.imagetoolbox.feature.erase_background.domain.model.BgModelType

internal object MlKitBackgroundRemoverBackend :
    AutoBackgroundRemoverBackend<Bitmap> by GenericBackgroundRemoverBackend(modelType = BgModelType.U2NetP)