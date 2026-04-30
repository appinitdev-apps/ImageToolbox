/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ksp.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class UiFilterInject(
    val group: String = Groups.UNSPECIFIED
) {
    object Groups {
        const val SIMPLE = "Simple"
        const val COLOR = "Color"
        const val LUT = "LUT"
        const val LIGHT = "Light"
        const val EFFECTS = "Effects"
        const val BLUR = "Blur"
        const val PIXELATION = "Pixelation"
        const val DISTORTION = "Distortion"
        const val DITHERING = "Dithering"
        const val UNSPECIFIED = "Unspecified"
    }
}