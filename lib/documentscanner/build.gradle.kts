/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
}

android.namespace = "com.websitebeaver.documentscanner"

dependencies {
    implementation(libs.opencv)
    implementation(libs.appCompat)
    implementation(libs.toolbox.exif)

    implementation(projects.lib.opencvTools)
}