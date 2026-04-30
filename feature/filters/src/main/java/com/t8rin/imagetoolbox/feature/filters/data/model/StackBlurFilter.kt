/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import com.t8rin.trickle.Trickle
import kotlin.math.roundToInt

@FilterInject
internal class StackBlurFilter(
    override val value: Pair<Float, Float> = 0.5f to 10f,
) : Transformation<Bitmap>, Filter.StackBlur {

    override val cacheKey: String
        get() = value.hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap = Trickle.stackBlur(
        bitmap = input,
        scale = value.first,
        radius = value.second.roundToInt()
    )

}