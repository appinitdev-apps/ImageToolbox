/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.domain

sealed class ProjectBackground {
    data object None : ProjectBackground()

    data class Image(
        val uri: String
    ) : ProjectBackground()

    data class Color(
        val width: Int,
        val height: Int,
        val color: Int
    ) : ProjectBackground()
}