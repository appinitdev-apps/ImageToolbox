/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
    alias(libs.plugins.image.toolbox.hilt)
}

android.namespace = "com.t8rin.imagetoolbox.core.filters"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.resources)
    implementation(projects.core.settings)
    implementation(projects.core.utils)
    implementation(projects.core.ksp)
    ksp(projects.core.ksp)

    implementation(projects.lib.curves)
    implementation(projects.lib.ascii)
    implementation(projects.lib.neuralTools)
    implementation(libs.trickle)
}