/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.core.settings"

dependencies {
    implementation(libs.datastore.preferences.android)
    implementation(libs.datastore.core.android)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.coil)

    implementation(projects.lib.dynamicTheme)
    implementation(projects.core.domain)
    implementation(projects.core.resources)
    implementation(projects.core.di)
}