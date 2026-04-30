/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ascii_art.data

import android.graphics.Bitmap
import com.t8rin.ascii.ASCIIConverter
import com.t8rin.ascii.Gradient
import com.t8rin.ascii.toMapper
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.feature.ascii_art.domain.AsciiConverter
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AndroidAsciiConverter @Inject constructor(
    dispatchersHolder: DispatchersHolder
) : AsciiConverter<Bitmap>, DispatchersHolder by dispatchersHolder {

    override suspend fun imageToAscii(
        image: Bitmap,
        fontSize: Float,
        gradient: String,
        isInverted: Boolean
    ): String = withContext(defaultDispatcher) {
        ASCIIConverter(
            fontSize = fontSize,
            mapper = Gradient(gradient).toMapper(),
            reverseLuma = isInverted
        ).convertToAscii(image)
    }

}