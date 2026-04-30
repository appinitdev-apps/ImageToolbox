/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.params.BloomParams
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import com.t8rin.trickle.Trickle

@FilterInject
internal class BloomFilter(
    override val value: BloomParams = BloomParams.Default
) : Transformation<Bitmap>, Filter.Bloom {

    override val cacheKey: String
        get() = value.hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap = Trickle.bloom(
        src = input,
        threshold = value.threshold,
        intensity = value.intensity,
        radius = value.radius,
        softKnee = value.softKnee,
        exposure = value.exposure,
        gamma = value.gamma
    )

}