/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.compose)
    alias(libs.plugins.image.toolbox.hilt)
}

android.namespace = "com.t8rin.imagetoolbox.core.utils"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.resources)
    implementation(projects.core.settings)
    implementation(libs.androidx.documentfile)
    "marketImplementation"(libs.quickie.bundled)
    "fossImplementation"(libs.quickie.foss)
    implementation(libs.zxing.core)
}