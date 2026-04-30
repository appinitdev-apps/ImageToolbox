/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.coil

import coil3.intercept.Interceptor
import coil3.request.ImageResult
import coil3.request.transformations
import com.t8rin.imagetoolbox.core.utils.makeLog

internal object TimeMeasureInterceptor : Interceptor {

    override suspend fun intercept(
        chain: Interceptor.Chain
    ): ImageResult {
        val time = System.currentTimeMillis()
        val result = chain.proceed()
        val endTime = System.currentTimeMillis()

        val delta = endTime - time

        val transformations = chain.request.transformations.joinToString(", ") {
            it.toString()
        }
        if (transformations.isNotEmpty()) {
            "Time $delta ms for transformations = $transformations, with ${result.request.sizeResolver.size()}".makeLog(
                "RealImageLoader"
            )
        }
        "Time $delta ms for ${chain.size}".makeLog("RealImageLoader")

        return result
    }

}