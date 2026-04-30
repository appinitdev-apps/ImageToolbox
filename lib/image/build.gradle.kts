/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.image"

dependencies {
    implementation(projects.lib.gesture)
    implementation(libs.androidx.palette.ktx)
}