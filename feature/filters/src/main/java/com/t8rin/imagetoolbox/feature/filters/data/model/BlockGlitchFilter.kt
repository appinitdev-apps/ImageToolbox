/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import com.t8rin.trickle.Trickle

@FilterInject
internal class BlockGlitchFilter(
    override val value: Pair<Float, Float> = 0.02f to 0.5f,
) : Transformation<Bitmap>, Filter.BlockGlitch {

    override val cacheKey: String
        get() = value.hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap = Trickle.blockGlitch(
        src = input,
        blockSizeFraction = value.first,
        strength = value.second
    )

}