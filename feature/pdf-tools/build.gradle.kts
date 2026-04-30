/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.feature)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.feature.pdf_tools"

dependencies {
    implementation(libs.androidx.pdfviewer.fragment)
    implementation(libs.androidx.fragment.compose)
    implementation(libs.trickle)
    implementation(libs.aire)
    implementation(libs.pdfbox)
}