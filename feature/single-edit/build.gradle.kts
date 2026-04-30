/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.feature)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.feature.single_edit"

dependencies {
    implementation(projects.feature.crop)
    implementation(projects.feature.eraseBackground)
    implementation(projects.feature.draw)
    implementation(projects.feature.filters)
    implementation(projects.feature.pickColor)
    implementation(projects.feature.compare)
    implementation(projects.lib.curves)
}