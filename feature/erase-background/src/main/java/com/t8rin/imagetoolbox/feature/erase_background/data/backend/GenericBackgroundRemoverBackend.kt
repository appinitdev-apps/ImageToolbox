/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.data.backend

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.feature.erase_background.domain.AutoBackgroundRemoverBackend
import com.t8rin.imagetoolbox.feature.erase_background.domain.model.BgModelType
import com.t8rin.neural_tools.bgremover.BgRemover

internal class GenericBackgroundRemoverBackend(
    private val modelType: BgModelType
) : AutoBackgroundRemoverBackend<Bitmap> {

    override suspend fun performBackgroundRemove(
        image: Bitmap
    ): Result<Bitmap> = runCatching {
        BgRemover.removeBackground(
            image = image,
            type = when (modelType) {
                BgModelType.MlKit,
                BgModelType.U2NetP -> BgRemover.Type.U2NetP

                BgModelType.U2Net -> BgRemover.Type.U2Net
                BgModelType.RMBG -> BgRemover.Type.RMBG1_4
                BgModelType.InSPyReNet -> BgRemover.Type.InSPyReNet
                BgModelType.BiRefNetTiny -> BgRemover.Type.BiRefNetTiny
                BgModelType.ISNet -> BgRemover.Type.ISNet
            }
        )!!
    }

}