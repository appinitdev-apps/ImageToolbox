/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.collages"

dependencies {
    implementation(libs.appCompat)
    implementation(libs.coilCompose)
}