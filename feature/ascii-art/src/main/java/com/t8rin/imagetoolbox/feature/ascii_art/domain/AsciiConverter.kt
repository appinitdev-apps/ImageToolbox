/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ascii_art.domain

interface AsciiConverter<Image> {

    suspend fun imageToAscii(
        image: Image,
        fontSize: Float,
        gradient: String,
        isInverted: Boolean
    ): String

}