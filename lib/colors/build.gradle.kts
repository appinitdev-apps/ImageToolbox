/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.colors"

dependencies {
    implementation(libs.androidx.palette.ktx)

    implementation(projects.lib.gesture)
    implementation(projects.lib.image)
    implementation(projects.lib.zoomable)
}