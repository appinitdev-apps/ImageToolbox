/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.core.crash"

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.settings)

    "marketImplementation"(platform(libs.firebase.bom))
    "marketImplementation"(libs.firebase.crashlytics)
    "marketImplementation"(libs.firebase.analytics)
}