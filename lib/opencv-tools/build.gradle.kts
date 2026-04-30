/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.opencv_tools"

dependencies {
    api(libs.opencv)
    implementation(libs.coilCompose)
    implementation(projects.lib.image)
    implementation(projects.lib.zoomable)
    implementation(projects.lib.gesture)
}