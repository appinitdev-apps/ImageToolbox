/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.data

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.t8rin.imagetoolbox.core.data.image.utils.healAlpha
import com.t8rin.imagetoolbox.core.domain.coroutines.AppScope
import com.t8rin.imagetoolbox.core.utils.makeLog
import com.t8rin.imagetoolbox.feature.erase_background.domain.AutoBackgroundRemover
import com.t8rin.imagetoolbox.feature.erase_background.domain.AutoBackgroundRemoverBackendFactory
import com.t8rin.imagetoolbox.feature.erase_background.domain.model.BgModelType
import com.t8rin.neural_tools.bgremover.BgRemover
import com.t8rin.trickle.TrickleUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AndroidAutoBackgroundRemover @Inject constructor(
    private val backendFactory: AutoBackgroundRemoverBackendFactory<Bitmap>,
    private val appScope: AppScope
) : AutoBackgroundRemover<Bitmap> {

    override suspend fun trimEmptyParts(
        image: Bitmap,
        emptyColor: Int?
    ): Bitmap = coroutineScope {
        val transparent = emptyColor ?: Color.Transparent.toArgb()

        runCatching {
            TrickleUtils.trimEmptyParts(
                bitmap = image,
                transparent = transparent
            )
        }.onFailure {
            "trimEmptyParts".makeLog("Failed to crop image ${it.message}")
        }.getOrNull() ?: image
    }

    override fun removeBackgroundFromImage(
        image: Bitmap,
        modelType: BgModelType,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        appScope.launch {
            backendFactory.create(modelType)
                .performBackgroundRemove(image)
                .map { it.healAlpha(image) }
                .onSuccess(onSuccess)
                .onFailure {
                    onFailure(it.makeLog())
                }
        }
    }

    override fun cleanup() = BgRemover.closeAll()

}