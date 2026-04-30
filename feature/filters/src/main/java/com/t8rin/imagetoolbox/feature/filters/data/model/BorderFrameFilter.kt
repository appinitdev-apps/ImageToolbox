/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import com.t8rin.imagetoolbox.core.data.image.utils.ColorUtils.toModel
import com.t8rin.imagetoolbox.core.data.image.utils.drawBitmap
import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.model.Position
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import kotlin.math.roundToInt

@FilterInject
internal class BorderFrameFilter(
    override val value: Triple<Float, Float, ColorModel> = Triple(20f, 40f, Color.White.toModel())
) : Transformation<Bitmap>, Filter.BorderFrame {
    override val cacheKey: String
        get() = value.hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap {
        val horizontal = value.first.roundToInt()
        val vertical = value.second.roundToInt()

        return createBitmap(
            width = input.width + horizontal * 2,
            height = input.height + vertical * 2
        ).applyCanvas {
            drawColor(value.third.colorInt)

            drawBitmap(
                bitmap = input,
                position = Position.Center
            )
        }
    }
}