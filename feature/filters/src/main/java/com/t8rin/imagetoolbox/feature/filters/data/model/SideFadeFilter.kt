/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import androidx.core.graphics.applyCanvas
import com.t8rin.imagetoolbox.core.data.utils.safeConfig
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.data.getPaint
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.FadeSide
import com.t8rin.imagetoolbox.core.filters.domain.model.params.SideFadeParams
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import kotlin.math.roundToInt

@FilterInject
internal class SideFadeFilter(
    override val value: SideFadeParams = SideFadeParams.Relative(FadeSide.Start, 0.5f),
) : Transformation<Bitmap>, Filter.SideFade {

    override val cacheKey: String
        get() = value.hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap {
        val bitmap = input.copy(input.safeConfig, true).apply { setHasAlpha(true) }
        val fadeSize: Int = when (value) {
            is SideFadeParams.Absolute -> value.size
            is SideFadeParams.Relative -> {
                when (value.side) {
                    FadeSide.Start, FadeSide.End -> {
                        bitmap.width * value.scale
                    }

                    FadeSide.Bottom, FadeSide.Top -> {
                        bitmap.height * value.scale
                    }
                }.roundToInt()
            }
        }
        val strength = when (value) {
            is SideFadeParams.Absolute -> value.strength
            is SideFadeParams.Relative -> 1f
        }

        return bitmap.applyCanvas {
            drawPaint(
                value.side.getPaint(
                    bmp = input,
                    length = fadeSize,
                    strength = strength
                )
            )
        }
    }

}