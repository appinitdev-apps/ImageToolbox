/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.feature)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.feature.filters"

dependencies {
    api(projects.core.filters)
    ksp(projects.core.ksp)
    implementation(projects.core.ksp)
    implementation(projects.feature.draw)
    implementation(projects.feature.pickColor)
    implementation(projects.feature.compare)
    implementation(libs.kotlin.reflect)
    implementation(libs.aire)
    implementation(libs.trickle)
    implementation(libs.toolbox.gpuimage)
    implementation(projects.lib.opencvTools)
    implementation(projects.lib.neuralTools)
    implementation(projects.lib.curves)
    implementation(libs.toolbox.jhlabs)
    implementation(projects.lib.ascii)
}