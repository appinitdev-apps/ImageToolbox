/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.ksp.annotations.FilterInject
import com.t8rin.opencv_tools.seam_carving.SeamCarver

@FilterInject
internal class DistortionFilter(
    override val value: Float = 50f
) : Transformation<Bitmap>, Filter.Distortion {

    override val cacheKey: String
        get() = value.toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap = SeamCarver.distort(
        bitmap = input,
        distortionPercent = value
    )

}