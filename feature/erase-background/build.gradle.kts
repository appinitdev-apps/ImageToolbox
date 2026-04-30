/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.feature)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.feature.erase_background"

dependencies {
    "marketImplementation"(libs.mlkit.subject.segmentation)
    "marketImplementation"(libs.mlkit.segmentation.selfie)

    implementation(projects.lib.neuralTools)
    implementation(libs.trickle)

    implementation(projects.feature.draw)
}