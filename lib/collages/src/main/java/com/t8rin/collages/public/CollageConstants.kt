/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.collages.public

import coil3.request.ImageRequest
import com.t8rin.collages.utils.CollageLayoutFactory

object CollageConstants {
    const val MAX_IMAGE_COUNT: Int = 20

    val layoutCount: Int = CollageLayoutFactory.COLLAGE_MAP.keys.size

    internal var requestMapper: ImageRequest.Builder.() -> ImageRequest.Builder = { this }

    fun requestMapper(mapper: ImageRequest.Builder.() -> ImageRequest.Builder) {
        this.requestMapper = mapper
    }
}