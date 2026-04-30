/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import com.t8rin.trickle.Trickle

@FilterInject
internal class CrtCurvatureFilter(
    override val value: Triple<Float, Float, Float> = Triple(0.25f, 0.65f, 0.015f),
) : Transformation<Bitmap>, Filter.CrtCurvature {

    override val cacheKey: String
        get() = value.hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap = Trickle.crtCurvature(
        src = input,
        curvature = value.first,
        vignette = value.second,
        chroma = value.third
    )

}