/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.curves"

dependencies {
    implementation(libs.coilCompose)
    implementation(libs.toolbox.gpuimage)
    implementation(libs.toolbox.histogram)
}