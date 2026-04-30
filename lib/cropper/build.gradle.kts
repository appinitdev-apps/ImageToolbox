/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.cropper"

dependencies {
    implementation(projects.lib.gesture)
    implementation(projects.core.resources)
}