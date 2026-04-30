/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.feature)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.feature.ascii_art"

dependencies {
    implementation(projects.feature.filters)
    implementation(projects.lib.ascii)
}