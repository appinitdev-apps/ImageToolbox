/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.feature)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.feature.recognize.text"

dependencies {
    implementation(projects.core.filters)
    implementation(projects.feature.pdfTools)
    implementation(projects.feature.singleEdit)
    implementation(libs.tesseract)
}